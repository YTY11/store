package com.yty.store.service.ex;

/**
 * @author Yang
 * @create 2023-05-30 18:29
 * @Description: 密码不正确异常
 */
public class PasswordWrongException extends ServiceException {
    public PasswordWrongException() {
        super();
    }

    public PasswordWrongException(String message) {
        super(message);
    }

    public PasswordWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordWrongException(Throwable cause) {
        super(cause);
    }

    protected PasswordWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
