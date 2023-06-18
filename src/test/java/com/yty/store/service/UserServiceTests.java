package com.yty.store.service;

import com.yty.store.entity.User;
import com.yty.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author Yang
 * @create 2023-05-30 15:54
 * @Description: 用户模块 业务层接口测试
 */
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void registerTest() {
        try {
            User user = new User();
            user.setUsername("demo");
            user.setPassword("123");

            userService.register(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            // 异常类的名称
            System.out.println(e.getClass().getSimpleName());
            // 异常信息
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void loginTest() {
        User demo2 = userService.login("demo2", "123");
        System.out.println(demo2);
    }

    @Test
    public void changePassword() {
        userService.changePassword(11, "123", "321", "管理员");

    }
    @Test
    public void findByUid() {
        System.err.println(userService.findByUid(1));

    }


    @Test
    public void changeUserInfo() {
        User user = new User();
        user.setPhone("110");
        userService.changeUserInfo(1, "demo",user );
    }
    @Test
    public void changeAvatarByUid(){
        User user = new User();
        user.setModifiedUser("管理员");
        user.setModifiedTime(new Date());
        userService.changeAvatarByUid(1, "/test", user);
    }

}
