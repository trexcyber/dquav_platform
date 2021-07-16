package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/7/14 - 16:40
 */
public class DocExistException extends ServiceException {
    public DocExistException() {
        super();
    }

    public DocExistException(String message) {
        super(message);
    }

    public DocExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocExistException(Throwable cause) {
        super(cause);
    }

    protected DocExistException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
