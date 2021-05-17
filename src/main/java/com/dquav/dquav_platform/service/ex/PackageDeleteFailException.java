package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/12 - 17:23
 */
public class PackageDeleteFailException extends ServiceException{

    public PackageDeleteFailException() {
        super();
    }

    public PackageDeleteFailException(String message) {
        super(message);
    }

    public PackageDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageDeleteFailException(Throwable cause) {
        super(cause);
    }

    protected PackageDeleteFailException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
