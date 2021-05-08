package com.dquav.dquav_platform.controller.ex;

/**
 * @author TrEx
 * @date 2021/5/8 - 15:50
 */
public class UploadException extends RuntimeException {
    public UploadException() {
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }

    public UploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
