package com.yty.store.controller.ex;

/**
 * @author Yang
 * @create 2023-05-31 13:37
 * @Description: 文件大小异常
 */
public class FileSizeException extends FileUploadException{
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
