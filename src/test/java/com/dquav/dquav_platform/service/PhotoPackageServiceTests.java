package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.rmi.ServerException;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/6/21 - 16:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoPackageServiceTests {

    @Resource
    IPackageService iPackageService;

    @Test
    public void savePackage(){
        try {
            Integer uid = 2;
            String activityName ="陕西投资集团全国企业会";
            String photoPackageName = "文件夹1.zip";
            String photoPackageSite ="d://文件夹.zip";
            iPackageService.savePackage(uid,activityName,photoPackageName,photoPackageSite);
            System.out.println("ok");

        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findPackageByActivityName(){
         try {
             String ActivityName = "陕西投资集团全国企业会";
             List<PhotoPackage> photoPackages=iPackageService.findPackageByActivityName(ActivityName);
             for (PhotoPackage photoPackage : photoPackages) {
                 System.out.println(photoPackage);
             }
         }catch (ServiceException e ){
             System.out.println(e.getClass().getName());
             System.out.println(e.getMessage());
         }
    }

    @Test
    public void findPackageByPhotoPackageName(){
        try {
            String photoPackageName ="文件夹1.zip";
                System.out.println(iPackageService.findPackageByPhotoPackageName(photoPackageName));
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removePackageByPhotoPackageName(){
        try {
            Integer uid = 3;
            String photoPackageName = "文件夹1.zip";
            iPackageService.removePackageByPhotoPackageName(uid,photoPackageName);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeAllPackageByActivityId(){
        try {
            Integer uid = 3;
            String activityName ="陕西投资集团全国企业会";
            iPackageService.removeAllPackageByActivityId(uid,activityName);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

}
