package com.dquav.dquav_platform.service.ex;

import javax.websocket.server.ServerEndpoint;

/**
 * @author TrEx
 * @date 2021/4/27 - 11:35
 *
 * 插入数据异常 原因不明确
 */
public class InsertException extends ServiceException {

    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
