package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/12 - 16:36
 */
public class PhotoDeleteFailException extends ServiceException {
    public PhotoDeleteFailException() {
        super();
    }

    public PhotoDeleteFailException(String message) {
        super(message);
    }

    public PhotoDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoDeleteFailException(Throwable cause) {
        super(cause);
    }

    protected PhotoDeleteFailException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
