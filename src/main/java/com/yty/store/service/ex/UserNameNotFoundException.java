package com.yty.store.service.ex;

/**
 * @author Yang
 * @create 2023-05-30 18:28
 * @Description: 用户名没有找到异常
 */
public class UserNameNotFoundException extends ServiceException{
    public UserNameNotFoundException() {
        super();
    }

    public UserNameNotFoundException(String message) {
        super(message);
    }

    public UserNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
