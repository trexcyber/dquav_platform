package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.ex.PasswordNotMatchException;
import com.dquav.dquav_platform.service.ex.UpdateException;
import com.dquav.dquav_platform.service.ex.UserLevelLimitFailException;
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
     * 根据用户id查询用户信息
     *
     * @param uid 用户id
     * @return 用户数据
     * @throws UserNotFoundException 未找到匹配的用户名
     */
    UserList getByUid(Integer uid) throws UserNotFoundException;


    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户数据
     * @throws UserNotFoundException 未找到匹配的用户名
     */
    UserList getByUsername(String username) throws UserNotFoundException;

    /**
     * 查询所有用户列表
     * @param uid 查询的用户id
     * @return 返回查询结果
     * @throws UserLevelLimitFailException 查询的用户等级不够
     */
    List<UserList> getUserList(Integer uid)throws UserLevelLimitFailException;

    /**
     * 修改用户信息
     *
     * @param userList 用户信息
     * @throws UserNotFoundException 用户未找到异常
     * @throws UpdateException 抛出未明确原因 更新异常
     */
    void changeInfo(UserList userList) throws UserNotFoundException, UpdateException;

//    注册 审批 接口
//    删除 用户 凭等级限制

}
