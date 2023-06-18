package com.yty.store.controller;

import com.yty.store.controller.ex.*;
import com.yty.store.service.ex.*;
import com.yty.store.utils.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author Yang
 * @create 2023-05-30 16:27
 * @Description: 控制层基类 统一处理控制层结果异常
 */
public class BaseController {
    public static final Integer OK = 200;


    /**
     * ExceptionHandler 自动将异常传递给此方法，用于统一处理抛出的异常
     * @param e 异常
     * @return 处理异常后的结果
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JSONResult<Void> handlerException(Throwable e){
        JSONResult<Void> jsonResult = new JSONResult<>(e);
        // 用户名已存在
        if (e instanceof UserNameOccupyException){
            jsonResult.setState(401);
            jsonResult.setMessage(e.getMessage());
        } else if (e instanceof InsertException) {
            // 服务器或数据库宕机等未知错误
            jsonResult.setState(500);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof UserNameNotFoundException) {
            // 用户不存在
            jsonResult.setState(501);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof PasswordWrongException) {
            // 密码错误
            jsonResult.setState(502);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof UpdateException) {
            // 修改数据异常
            jsonResult.setState(503);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof AddressCountException) {
            // 地址值超出上限
            jsonResult.setState(504);
            jsonResult.setMessage(e.getMessage());
        }

        else if (e instanceof FileSizeException) {
            jsonResult.setState(600);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof FileTypeException) {
            jsonResult.setState(601);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof FileStateException) {
            jsonResult.setState(602);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof FileUploadIOException) {
            jsonResult.setState(603);
            jsonResult.setMessage(e.getMessage());
        }
        else if (e instanceof FileEmptyException) {
            jsonResult.setState(604);
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;

    }

    /**
     *
     * @param httpSession 全局session
     * @return 获取用户 uid
     */
    protected final Integer getUidSession(HttpSession httpSession){
        return Integer.valueOf(httpSession.getAttribute("uid").toString());
    }

    /**
     *
     * @param httpSession 全局session
     * @return 获取用户名
     */
    protected final String getUserNameSession(HttpSession httpSession){
        return httpSession.getAttribute("username").toString();
    }

}
