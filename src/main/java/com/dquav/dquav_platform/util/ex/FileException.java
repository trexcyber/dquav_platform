package com.dquav.dquav_platform.util.ex;

/**
 * @author TrEx
 * @date 2021/7/5 - 15:34
 */
public class FileException extends RuntimeException {
    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

    protected FileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
