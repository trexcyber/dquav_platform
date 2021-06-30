package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.JsonResult;
import com.dquav.dquav_platform.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/6/28 - 16:53
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    IUserListService iUserListService;

    private JsonResult jsonResult = new JsonResult();


    @PostMapping("login")
    public ResponseResult<String>login(String username, String password, HttpSession session){
        String user =iUserListService.login(username, password);
        session.setAttribute("uid", jsonResult.getUserUid(user));
        session.setAttribute("username",jsonResult.getUsername(user));
        return new ResponseResult<>(SUCCESS,user);
    }

    @PostMapping("info")
    public ResponseResult<UserList>info(HttpSession session){
        UserList user=iUserListService.getByUid(getUidFromSession(session));
        return new ResponseResult<>(SUCCESS,user);
    }

    @PostMapping("change_info")
    public ResponseResult<Void>changeInfo(UserList userList,HttpSession session){
        iUserListService.getByUid(getUidFromSession(session));
        iUserListService.changeInfo(userList);
        return new ResponseResult<>(SUCCESS);
    }

    public ResponseResult<List<UserList>>getUserList(HttpSession session){
        return new ResponseResult<>(SUCCESS,iUserListService.getUserList(getUidFromSession(session)));
    }


}
