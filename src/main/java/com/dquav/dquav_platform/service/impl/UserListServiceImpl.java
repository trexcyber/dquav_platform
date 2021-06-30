package com.dquav.dquav_platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.UserLevelMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.service.ex.PasswordNotMatchException;
import com.dquav.dquav_platform.service.ex.UpdateException;
import com.dquav.dquav_platform.service.ex.UserLevelLimitFailException;
import com.dquav.dquav_platform.service.ex.UserNotFoundException;
import com.dquav.dquav_platform.util.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author TrEx
 * @date 2021/4/29 - 11:39
 * <p>
 * 处理用户业务层的实现类
 */
@Service
public class UserListServiceImpl implements IUserListService {

    @Resource
    UserListMapper userListMapper;
    @Resource
    UserLevelMapper userLevelMapper;

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
    public UserList getByUid(Integer uid) throws UserNotFoundException {
        UserList result = userListMapper.getUserListById(uid);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }

        return result;
    }

    @Override
    public List<UserList> getUserList(Integer uid) throws UserLevelLimitFailException {
        Map<String, String> user = userListMapper.getUserLevelByUid(uid);
        String levelName = String.valueOf(user.get("levelName"));
        String lName1 = userLevelMapper.findUserLevel(1).getLevelName();
        String lName2 = userLevelMapper.findUserLevel(2).getLevelName();
        if (!levelName.equals(lName1)||!levelName.equals(lName2)){
            throw new UserLevelLimitFailException("用户等级不够");
        }
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
        Integer uid = result.getUid();
        Integer lid = result.getLid();
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
