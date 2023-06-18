package com.yty.store.service.ex;

/**
 * @author Yang
 * @create 2023-05-30 15:19
 * @Description: 用户名占用异常
 */
public class UserNameOccupyException extends ServiceException{
    public UserNameOccupyException() {
        super();
    }

    public UserNameOccupyException(String message) {
        super(message);
    }

    public UserNameOccupyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameOccupyException(Throwable cause) {
        super(cause);
    }

    protected UserNameOccupyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
