package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/4/27 - 15:38
 */
public class ActivityNotFoundException extends ServiceException {
    public ActivityNotFoundException() {
        super();
    }

    public ActivityNotFoundException(String message) {
        super(message);
    }

    public ActivityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ActivityNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
