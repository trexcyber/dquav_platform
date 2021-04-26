package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户列表的持久层接口
 *
 * @author TrEx
 * @date 2021/3/24 - 11:32
 */
public interface UserListMapper {

    /**
     * 添加用户数据
     *
     * @param userList 用户数据
     * @return 返回受影响行数
     */
    Integer addUserList(UserList userList);

    /**
     * 查询用户
     *
     * @return 用户数据
     */
    List<UserList> getUserList();

    /**
     * 根据用户名 查找用户数据
     *
     * @param userName 用户名
     * @return 返回用户数据
     */
    UserList getUserListByUsername(String userName);

    /**
     * 删除用户数据
     *
     * @param uid 用户id
     * @return 返回受影响行数
     */
    Integer deleteUserByUid(Integer uid);

    /**
     * 根据用户id 修改用户数据
     *
     * @param uid       用户id
     * @param lid       等级id
     * @param username  用户名
     * @param password  密码
     * @param name      姓名
     * @param telephone 联系电话
     * @return 受影响行数
     */
    Integer updateUserByUid(@Param("uid") Integer uid,
                            @Param("lid") Integer lid,
                            @Param("username") String username,
                            @Param("password") String password,
                            @Param("name") String name,
                            @Param("telephone") String telephone);


}
