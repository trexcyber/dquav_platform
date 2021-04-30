package com.dquav.dquav_platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.service.ex.PasswordNotMatchException;
import com.dquav.dquav_platform.service.ex.UpdateException;
import com.dquav.dquav_platform.service.ex.UserNotFoundException;
import com.dquav.dquav_platform.util.JsonResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/29 - 11:39
 * <p>
 * 处理用户业务层的实现类
 */
public class UserListServiceImpl implements IUserListService {

    @Resource
    UserListMapper userListMapper;

    @Override
    public String login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        UserList result = userListMapper.getUserListByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (!password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码不正确");
        }
        JsonResult jsonResult = new JsonResult();

        return jsonResult.userListJsonFilter(result);
    }

    @Override
    public UserList getByUsername(String username) throws UserNotFoundException {
        UserList result = userListMapper.getUserListByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }

        return result;
    }

    @Override
    public List<UserList> getUserList() {
        List<UserList> result = userListMapper.getUserList();
        JsonResult jsonResult = new JsonResult();
        for (UserList userList : result) {
            jsonResult.userListJsonFilter(userList);
        }

        return result;
    }

    @Override
    public void changeInfo(UserList userList) throws UserNotFoundException, UpdateException {
        UserList result =userListMapper.getUserListByUsername(userList.getUsername());
        if (result == null){
            throw new UserNotFoundException("用户不存在");
        }
        Integer uid = userList.getUid();
        Integer lid = userList.getLid();
        String username = userList.getUsername();
        String password = userList.getPassword();
        String name = userList.getName();
        String telephone = userList.getTelephone();
        Integer rows = userListMapper.updateUserByUid(uid, lid, username, password, name, telephone);

        if (rows != 1) {
            throw new UpdateException("用户信息更新异常");
        }
    }
}
