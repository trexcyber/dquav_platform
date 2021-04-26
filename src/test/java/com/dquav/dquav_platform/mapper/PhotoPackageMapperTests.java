package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.PhotoPackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author TrEx
 * @date 2021/4/26 - 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoPackageMapperTests {

    @Resource
    PhotoPackageMapper photoPackageMapper;

    @Test
    public void addPhotoPackage(){
        PhotoPackage photoPackage = new PhotoPackage();
        photoPackage.setActivityId(1);
        photoPackage.setPhotoPackageName("陕投三会活动现场照片");
        photoPackage.setPhotoPackageSite("c://photo/stsh");
        photoPackage.setIsGuest(1);
        Integer rows = photoPackageMapper.addPhotoPackage(photoPackage);
        System.out.println(rows);
    }

}
