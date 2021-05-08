package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/6 - 17:10
 */
public class DocNotFoundException extends ServiceException {
    public DocNotFoundException() {
        super();
    }

    public DocNotFoundException(String message) {
        super(message);
    }

    public DocNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DocNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
