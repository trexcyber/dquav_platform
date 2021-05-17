package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/12 - 16:37
 */
public class PhotoNotFoundException extends ServiceException {
    public PhotoNotFoundException() {
        super();
    }

    public PhotoNotFoundException(String message) {
        super(message);
    }

    public PhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PhotoNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
