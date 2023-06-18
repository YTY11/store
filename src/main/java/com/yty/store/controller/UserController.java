package com.yty.store.controller;

import com.yty.store.controller.ex.*;
import com.yty.store.entity.User;
import com.yty.store.service.UserService;
import com.yty.store.utils.JSONResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Yang
 * @create 2023-05-30 16:23
 * @Description: 控制层
 */
@RestController
@RequestMapping("/api")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    /**
     * 注册接口
     * @param user {username: ***,password: ***}
     * @return 注册成功与否的信息
     */
    @PostMapping("/register")
    public JSONResult<Void> register(User user){
        userService.register(user);
        return new JSONResult<>(OK,"用户注册成功！");
    }

    /**
     * 登录接口
     * @param username
     * @param password
     * @param httpSession
     * @return 用户数据
     */
    @PostMapping("/login")
    public JSONResult<User> login(String username, String password, HttpSession httpSession){
        User user = userService.login(username, password);
        // 设置全局session 存储 uid 和 username
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setAttribute("username", user.getUsername());

        return new JSONResult<>(OK,"登录成功！",user);
    }

    /**
     * 修改密码接口
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    @PostMapping("/changePassword")
    public JSONResult<Void> changePassword(String oldPassword, String newPassword, HttpSession httpSession){
        // 获取uid
        Integer uid = getUidSession(httpSession);
        // 获取用户名
        String username = getUserNameSession(httpSession);
        // 修改数据
        userService.changePassword(uid,oldPassword,newPassword,username);

        return new JSONResult<>(OK,"修改成功");
    }

    /**
     * 根据uid查询用户数据接口
     * @param httpSession
     * @return 用户数据
     */
    @GetMapping("/findByUid")
    public JSONResult<User> findByUid(HttpSession httpSession){
        User user = userService.findByUid(getUidSession(httpSession));
        return new JSONResult<>(OK,"获取成功", user);
    }

    /**
     * 修改用户信息接口
     * @param user
     * @param httpSession
     * @return
     */
    @PutMapping("/changeUserInfo")
    public JSONResult<Void> changeUserInfo(User user,HttpSession httpSession){
        Integer uid = getUidSession(httpSession);
        String username = getUserNameSession(httpSession);

        userService.changeUserInfo(uid,username,user);
        return new JSONResult<>(OK,"修改成功");
    }

    // 定义最多头像文件大小
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    // 定义头像文件格式
    public static final ArrayList<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * 上传头像
     * @param user
     * @param httpSession
     * @param file
     * @return
     */
    @PostMapping("/avatarUpload")
    public JSONResult<String> avatarUpload(User user, HttpSession httpSession, MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }

        // 判断文件大小是否超出限制
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件过大超出限制");
        }

        // 判断文件类型是否符合要求
        String type = file.getContentType();
        if(!AVATAR_TYPE.contains(type)){
            throw new FileTypeException("文件类型不对");
        }
        // 上传文件 ../upload/..png
        String uploadPath = httpSession.getServletContext().getRealPath("upload");
        // file 指向这个路径判断file是否存在
        File dir = new File(uploadPath);
        if(!dir.exists()){
            // 目录不存
            dir.mkdirs();// 创建目录
        }
        
        // 使用UUID 生成新的文件名 并替换
        // 原名
        String originalFilename = file.getOriginalFilename();
        // 获取后缀名
        String suffixName = originalFilename.substring(originalFilename.indexOf("."));
        // 新名
        String newFileName = UUID.randomUUID().toString().toUpperCase() + suffixName;

        // 将空文件
        File dest = new File(dir, newFileName);

        // 将file中的文件写入dest
        try {
            file.transferTo(dest);
        }
        catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }catch (IOException e) {
            throw new FileUploadIOException("文件上传失败");
        }

        String path = "/upload/" + newFileName;

        userService.changeAvatarByUid(getUidSession(httpSession),path,user);

        return new JSONResult<>(OK, "修改成功",path);
    }
}
