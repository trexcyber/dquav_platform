package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.GuestPhoto;
import com.dquav.dquav_platform.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/19 - 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestPhotoServiceTests {

    @Resource
    IGuestPhotoService iGuestPhotoService;
    @Test
    public void savePhoto(){
        try {
            Integer uid = 3;
            String activityName ="陕西投资集团全国企业会";
            String photoAdds ="d://123.jpg";
            iGuestPhotoService.savePhoto(uid,activityName,photoAdds);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findPhotoByActivityName(){
        try {
            String activityName = "陕西投资集团全国企业会";
            List<GuestPhoto> photoResult =iGuestPhotoService.findPhotoByActivityName(activityName);
            for (GuestPhoto guestPhoto : photoResult) {
                System.out.println(guestPhoto);
            }
        }catch (ServiceException e ){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deletePhotoByPid(){
        try {
            Integer uid = 2;
            Integer pid =1;
            iGuestPhotoService.deletePhotoByPid(uid,pid);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }


}
