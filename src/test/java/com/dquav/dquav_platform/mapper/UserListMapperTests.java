package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.UserList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/26 - 14:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserListMapperTests {

    @Resource
    UserListMapper userListMapper;

    @Test
    public void addUserList() {
        UserList user = new UserList();
        user.setLid(1);
        user.setUsername("fyun");
        user.setPassword("dquav123");
        user.setName("方云");
        user.setTelephone("13319233029");
        Integer rows = userListMapper.addUserList(user);
        System.out.println(rows);
    }

    @Test
    public void getUserList(){
        List<UserList> userLists = userListMapper.getUserList();
        for (UserList userList : userLists) {
            System.out.println(userList);
        }
    }

    @Test
    public void getUserListByUsername() {
        String username = "trexcyber";
        UserList userList = userListMapper.getUserListByUsername(username);
        System.out.println(userList);
    }

    @Test
    public void deleteUserByUid() {
        Integer uid = 1;
        Integer rows = userListMapper.deleteUserByUid(uid);
        System.out.println(rows);
    }

    @Test
    public void updateUserByUid() {

        Integer uid = 2;
        Integer lid = 1;
        String username = "trexcyber";
        String password = "dquav";
        String name ="高壮";
        String telephone = "18992879000";

        Integer rows = userListMapper.updateUserByUid(uid,lid,username,password,name,telephone);
        System.out.println(rows);
    }

}
