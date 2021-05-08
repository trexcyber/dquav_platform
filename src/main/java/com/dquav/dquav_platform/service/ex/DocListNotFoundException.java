package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/6 - 16:54
 */
public class DocListNotFoundException extends ServiceException {
    public DocListNotFoundException() {
        super();
    }

    public DocListNotFoundException(String message) {
        super(message);
    }

    public DocListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocListNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DocListNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
