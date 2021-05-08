package com.dquav.dquav_platform.service.ex;

/**
 * @author TrEx
 * @date 2021/5/8 - 10:59
 *
 * 文档删除失败异常
 */
public class DocDeleteFailException extends ServiceException {
    public DocDeleteFailException() {
        super();
    }

    public DocDeleteFailException(String message) {
        super(message);
    }

    public DocDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocDeleteFailException(Throwable cause) {
        super(cause);
    }

    protected DocDeleteFailException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
