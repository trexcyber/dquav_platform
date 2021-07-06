package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/6/22 - 10:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserListServiceTests {

    @Resource
    IUserListService iUserListService;

    @Test
    public void login() {
        try {
            String username = "trex";
            String password = "000";
            String userList = iUserListService.login(username, password);
            System.out.println(userList);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        try {
            Integer uid = 3;
            UserList user = iUserListService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUsername() {
        try {
            String username ="trexcyber";
            UserList userList=iUserListService.getByUsername(username);
            System.out.println(userList);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getUserList(){
        try {
            List<UserList> userList=iUserListService.getUserList(1);
            for (UserList list : userList) {
                System.out.println(list);
            }
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        try {
            UserList userList = new UserList();
            userList.setUsername("trexcyber");
            userList.setName("高壮（测试）");
            userList.setPassword("000");
            userList.setTelephone("18000");
            iUserListService.changeInfo(userList);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }
}
