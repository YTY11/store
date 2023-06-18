package com.yty.store.mapper;

import com.yty.store.entity.User;

import java.util.Date;

/**
 * @author Yang
 * @create 2023-05-30 13:59
 * @Description:
 */
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     * @return 返回影响的行数
     */
    Integer insert(User user);

    /**
     *
     * @param userName
     * @return 返回查询到的用户信息
     */
    User findByUserName(String userName);

    /**
     *
     * @param uid 用户id
     * @return 返回用户信息
     */
    User findByUid(Integer uid);

    /**
     * 根据uid 修改用户密码
     * @param uid 用户id
     * @param password 新密码
     * @param modifiedUser 修改人员
     * @param modifiedTime 修改时间
     * @return 影响行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 修改用户数据
     * @param user
     * @return 影响行数
     */
    Integer updateUserInfoByUid(User user);

    Integer updateAvatarByUid(User user);
}
