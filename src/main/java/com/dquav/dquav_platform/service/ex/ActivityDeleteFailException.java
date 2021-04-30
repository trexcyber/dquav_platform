package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/4/27 - 16:23
 *
 * 删除活动异常 当尝试删除活动项目时未能删除
 */
public class ActivityDeleteFailException extends ServiceException {

    public ActivityDeleteFailException() {
        super();
    }

    public ActivityDeleteFailException(String message) {
        super(message);
    }

    public ActivityDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityDeleteFailException(Throwable cause) {
        super(cause);
    }

    protected ActivityDeleteFailException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
