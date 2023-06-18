package com.yty.store.service.impl;

import com.yty.store.entity.User;
import com.yty.store.mapper.UserMapper;
import com.yty.store.service.UserService;
import com.yty.store.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author Yang
 * @create 2023-05-30 15:26
 * @Description: 用户模块接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 获取注册的用户名
        String username = user.getUsername();

        // 判断用户名是否已存在
        User byUserName = userMapper.findByUserName(username);
        // byUserName!=null 有户名已存在
        if(byUserName != null){
            throw  new UserNameOccupyException("用户名已存在");
        }

        // 使用UUID 生成随机字符串作为盐值 通过md5：盐值 + password + 盐值 进行密码加密
        String sald = UUID.randomUUID().toString().toUpperCase();
        // 加密后的密码
        String newPassword = md5Password(user.getPassword(), sald);

        // 补充用户的其他信息
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setSalt(sald);
        user.setPassword(newPassword);

        // 用户名不存在 使用insert 进行注册
        Integer row = userMapper.insert(user);

        // 判断是否插入成功 row != 1插入失败
        if(row != 1){
            throw new InsertException("注册失败，请稍后重试");
        }

    }

    @Override
    public User login(String username, String password) {
        // 判断用户名是否存在
        User byUserName = userMapper.findByUserName(username);

        // 没有该用户
        if(byUserName == null){
            throw new UserNameNotFoundException("该用户名未找到");
        } else if (byUserName.getIsDelete() == 1) {
            // 该用户已注销
            throw new UserNameNotFoundException("该用户名未找到");
        }

        // 验证密码是否正确
        String salt = byUserName.getSalt();// 获取盐值
        String oldPassword = byUserName.getPassword(); // 数据库中的密码
        String newMd5Password = md5Password(password, salt);
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordWrongException("密码错误");
        }

        // 因为返回的数据过多，不需要这么多，为了提高系统性能，减少传递的数据量
        User user = new User();
        user.setUsername(byUserName.getUsername());
        user.setAvatar(byUserName.getAvatar());
        user.setUid(byUserName.getUid());
        return user;
    }

    /**
     *
     * @param uid 用户uid
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param username 修改人
     */
    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword, String username) {
        // 通过uid 获取用户数据判断用户是否存在
        User user = userMapper.findByUid(uid);

        if(user == null || user.getIsDelete() == 1){
            throw new UserNameNotFoundException("用户不存在");
        }

        // 旧密码进行比较判断密码是否正确
        if(!user.getPassword().equals(md5Password(oldPassword, user.getSalt()))){
            throw new PasswordWrongException("密码输入有误");
        }
        // 修改密码
        Integer row = userMapper.updatePasswordByUid(uid, md5Password(newPassword, user.getSalt()), username, new Date());

        if(row != 1){
            throw new UpdateException("修改失败, 请稍后重试");
        }
    }

    /**
     * 根据uid 查询用户
     * @param uid 用户uid
     * @return 用户信息
     */
    @Override
    public User findByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1){
            throw new UserNameNotFoundException("用户不存在");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        return newUser;
    }

    /**
     * 修改用户数据
     * @param uid 用户uid
     * @param username 用户名
     * @param user 用户数据
     */
    @Override
    public void changeUserInfo(Integer uid, String username, User user) {
        User oldUser = findByUid(uid);

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer row = userMapper.updateUserInfoByUid(user);
        if(row != 1){
            throw new UpdateException("修改失败");
        }
    }

    @Override
    public void changeAvatarByUid(Integer uid, String avatar, User user) {
        User oldUser = findByUid(uid);

        user.setUid(uid);
        user.setAvatar(avatar);
        user.setModifiedTime(new Date());
        user.setUsername(oldUser.getUsername());
        Integer row = userMapper.updateAvatarByUid(user);

        if(row != 1){
            throw new UpdateException("头像修改失败");
        }
    }

    /**
     * md5 加密密码
     * @param password 密码明文
     * @param salt 盐值
     * @return 加密后的密码
     */
    public String md5Password(String password, String salt){
        // 3次加密，提高加密强度
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
