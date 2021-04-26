package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.GuestPhoto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/26 - 17:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestPhotoMapperTests {

    @Resource
    GuestPhotoMapper guestPhotoMapper;

    @Test
    public void addPhoto() {
        GuestPhoto guestPhoto = new GuestPhoto();
        guestPhoto.setActivityId(1);
        guestPhoto.setPhotoAdds("c://photo/stsh/12fsd3.jpg");
        Integer rows = guestPhotoMapper.addPhoto(guestPhoto);
        System.out.println(rows);
    }

    @Test
    public void getPhotoByActivityId() {
        Integer activityId = 1;
        List<GuestPhoto> guestPhotos = guestPhotoMapper.getPhotoByActivityId(activityId);
        for (GuestPhoto guestPhoto : guestPhotos) {
            System.out.println(guestPhoto);
        }
    }

    @Test
    public void deletePhotoByPid() {
        Integer pid = 1;
        Integer rows = guestPhotoMapper.deletePhotoByPid(pid);
        System.out.println(rows);
    }

    @Test
    public void deletePhotoById() {
        Integer activityId = 1;
        Integer rows = guestPhotoMapper.deletePhotoById(activityId);
        System.out.println(rows);
    }

}
