package com.yty.store.mapper;

import com.yty.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Yang
 * @create 2023-05-30 14:20
 * @Description: 持久层接口测试
 */
@SpringBootTest
public class UserMapperTests {
    @Resource
    private UserMapper userMapper;

    /**
     * 测试添加用户接口
     */
    @Test
    public void insertTest() {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /**
     * 测试根据userName查询用户数据接口
     */
    @Test
    public void findByUserNameTest() {
        User demo = userMapper.findByUserName("demo");
        System.out.println(demo);
    }

    /**
     * 根据uid 修改用户密码
     */
    @Test
    public void updatePasswordByUidTest() {
        Integer row = userMapper.updatePasswordByUid(2, "123", "管理员", new Date());
        System.out.println(row);
    }

    /**
     * 根据uid查找用户
     */
    @Test
    public void findByUidTest() {
        User user = userMapper.findByUid(2);
        System.out.println(user);
    }

    @Test
    public void updateUserInfoByUidTest() {
        User user = new User();
        user.setUid(1);
        user.setPhone("1353565321");
        user.setEmail("865@aa.com");
        user.setGender(1);
        userMapper.updateUserInfoByUid(user);
    }
    @Test
    void updateAvatarByUid(){
        User user = new User();
        user.setUid(1);
        user.setAvatar("1353565321");
        userMapper.updateAvatarByUid(user);
    }
}
