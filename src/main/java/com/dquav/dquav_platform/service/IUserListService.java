package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.ex.PasswordNotMatchException;
import com.dquav.dquav_platform.service.ex.UpdateException;
import com.dquav.dquav_platform.service.ex.UserNotFoundException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/29 - 11:22
 * <p>
 * 处理用户数据的业务层接口
 */
public interface IUserListService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回登陆后的用户信息
     * @throws UserNotFoundException     没找到匹配的用户名
     * @throws PasswordNotMatchException 密码输入不正确
     */
    String login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户数据
     * @throws UserNotFoundException 未找到匹配的用户名
     */
    UserList getByUsername(String username) throws UserNotFoundException;

    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    List<UserList> getUserList();

    /**
     * 修改用户信息
     *
     * @param userList 用户信息
     * @throws UpdateException 抛出未明确原因 更新异常
     */
    void changeInfo(UserList userList) throws UserNotFoundException, UpdateException;


}
