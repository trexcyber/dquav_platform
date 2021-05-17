package com.dquav.dquav_platform.controller.ex;

/**
 * @author TrEx
 * @date 2021/5/11 - 14:54
 */
public class FileUploadIoException extends UploadException{
    public FileUploadIoException() {
        super();
    }

    public FileUploadIoException(String message) {
        super(message);
    }

    public FileUploadIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIoException(Throwable cause) {
        super(cause);
    }

    public FileUploadIoException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
