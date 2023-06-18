package com.yty.store.service;

import com.yty.store.entity.User;

/**
 * @author Yang
 * @create 2023-05-30 15:23
 * @Description: 用户模块业务接口
 */
public interface UserService {
    /**
     * 用户注册方法
     * @param user
     */
    void register(User user);

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param uid 用户uid
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param username 修改人
     */
    void changePassword(Integer uid, String oldPassword, String newPassword, String username);

    /**
     * 根据uid 查询用户数据
     * @param uid 用户uid
     * @return 用户数据
     */
    User findByUid(Integer uid);

    /**
     * 修改用户数据
     * @param uid 用户uid
     * @param username 用户名
     * @param user 用户数据
     */
    void changeUserInfo(Integer uid, String username, User user);

    /**
     * 修改头像
     * @param uid
     * @param avatar
     * @param user
     */
    void changeAvatarByUid(Integer uid, String avatar, User user);
}
