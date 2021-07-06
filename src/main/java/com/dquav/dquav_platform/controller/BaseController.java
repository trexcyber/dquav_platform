package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.*;
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
        if (e instanceof ActivityDeleteFailException){
            responseResult.setState(410);
        }
        if (e instanceof PackageDeleteFailException){
            responseResult.setState(411);
        }
        if (e instanceof PackageNotFoundException){
            responseResult.setState(412);
        }
        if (e instanceof PackageNullException){
            responseResult.setState(413);
        }
        if (e instanceof PasswordNotMatchException){
            responseResult.setState(414);
        }
        if (e instanceof PhotoDeleteFailException){
            responseResult.setState(415);
        }
        if (e instanceof PhotoNotFoundException){
            responseResult.setState(416);
        }
        if (e instanceof ServiceException){
            responseResult.setState(500);
        }
        if (e instanceof UpdateException){
            responseResult.setState(501);
        }
        if (e instanceof UserLevelLimitFailException){
            responseResult.setState(417);
        }
        if (e instanceof FileSizeException){
            responseResult.setState(505);
        }
        if (e instanceof FileStateException){
            responseResult.setState(506);
        }
        if (e instanceof FileUploadIoException){
            responseResult.setState(507);
        }
        if (e instanceof FlieEmptyException){
            responseResult.setState(508);
        }
        if (e instanceof UploadException){
            responseResult.setState(509);
        }
        return responseResult;
    }

}
