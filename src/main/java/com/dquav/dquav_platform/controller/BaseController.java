package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.UploadException;
import com.dquav.dquav_platform.service.ex.*;
import com.dquav.dquav_platform.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author TrEx
 * @date 2021/5/8 - 15:17
 */
public abstract class BaseController {

    protected static final Integer SUCCESS = 200;

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return String.valueOf(session.getAttribute("username").toString());
    }

    protected final Integer getActivityIdFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("activityId").toString());
    }

    protected final String getActivityNameFromSession(HttpSession session){
        return String.valueOf(session.getAttribute("activityName").toString());
    }



    @ExceptionHandler({ServiceException.class, UploadException.class})
    public ResponseResult<Void> handleException (Throwable e){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.setMessage(e.getMessage());
        if (e instanceof InsertException){
            responseResult.setState(301);
        }
        if (e instanceof DocDeleteFailException){
            responseResult.setState(302);
        }
        if (e instanceof ActivityNotFoundException){
            responseResult.setState(404);
        }
        if (e instanceof ActivityListNotFoundException){
            responseResult.setState(406);
        }
        if (e instanceof DocListNotFoundException){
            responseResult.setState(407);
        }
        if (e instanceof DocNotFoundException){
            responseResult.setState(408);
        }
        if (e instanceof UserNotFoundException){
            responseResult.setState(409);
        }
        return responseResult;
    }

}
