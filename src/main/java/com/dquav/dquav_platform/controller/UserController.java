package com.dquav.dquav_platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.JsonResult;
import com.dquav.dquav_platform.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping("login")
    public ResponseResult<JSONObject>login(String username, String password, HttpSession session){
        JSONObject user =iUserListService.login(username, password);
        System.out.println(user);
        session.setAttribute("uid", user.getString("uid"));
        session.setAttribute("username",user.getString("username"));
//        session.setAttribute("username",jsonResult.getUsername(user));
        return new ResponseResult<>(SUCCESS, user);
    }

    @PostMapping("info")
    public ResponseResult<UserList>info(HttpSession session){
        UserList user=iUserListService.getByUid(getUidFromSession(session));
        return new ResponseResult<>(SUCCESS,user);
    }

    @PostMapping("change_info")
    public ResponseResult<Void>changeInfo(@RequestBody UserList userList, HttpSession session){
        iUserListService.getByUid(getUidFromSession(session));
        iUserListService.changeInfo(userList);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("list")
    public ResponseResult<List<UserList>>getUserList(HttpSession session){
        return new ResponseResult<>(SUCCESS,iUserListService.getUserList(getUidFromSession(session)));
    }


}
