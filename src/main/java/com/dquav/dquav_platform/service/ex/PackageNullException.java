package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/6/21 - 16:52
 */
public class PackageNullException extends ServiceException {
    public PackageNullException() {
        super();
    }

    public PackageNullException(String message) {
        super(message);
    }

    public PackageNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageNullException(Throwable cause) {
        super(cause);
    }

    protected PackageNullException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
