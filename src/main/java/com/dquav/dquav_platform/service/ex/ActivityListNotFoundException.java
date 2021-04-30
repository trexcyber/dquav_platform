package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/4/27 - 15:43
 */
public class ActivityListNotFoundException extends ServiceException {
    public ActivityListNotFoundException() {
        super();
    }

    public ActivityListNotFoundException(String message) {
        super(message);
    }

    public ActivityListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityListNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ActivityListNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
