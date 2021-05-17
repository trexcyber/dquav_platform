package com.dquav.dquav_platform.controller.ex;

/**
 * @author TrEx
 * @date 2021/5/11 - 15:30
 */
public class FlieEmptyException extends UploadException {
    public FlieEmptyException() {
    }

    public FlieEmptyException(String message) {
        super(message);
    }

    public FlieEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlieEmptyException(Throwable cause) {
        super(cause);
    }

    public FlieEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
