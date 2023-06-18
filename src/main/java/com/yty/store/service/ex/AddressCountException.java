package com.yty.store.service.ex;

/**
 * @author Yang
 * @create 2023-05-31 19:20
 * @Description: 地址数超出上限
 */
public class AddressCountException extends ServiceException{
    public AddressCountException() {
        super();
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

    protected AddressCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
