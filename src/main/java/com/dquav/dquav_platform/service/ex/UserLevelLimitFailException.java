package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/12 - 17:33
 */
public class UserLevelLimitFailException extends ServiceException {
    public UserLevelLimitFailException() {
        super();
    }

    public UserLevelLimitFailException(String message) {
        super(message);
    }

    public UserLevelLimitFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLevelLimitFailException(Throwable cause) {
        super(cause);
    }

    protected UserLevelLimitFailException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
