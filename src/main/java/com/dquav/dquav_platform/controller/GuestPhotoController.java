package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.FileSizeException;
import com.dquav.dquav_platform.controller.ex.FileStateException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.GuestPhoto;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IGuestPhotoService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.ResponseResult;
import javafx.application.Application;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/7/1 - 11:10
 */
@RestController
@RequestMapping("photo")
public class GuestPhotoController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Resource
    IGuestPhotoService iGuestPhotoService;
    @Resource
    IUserListService iUserListService;
    @Resource
    IActivityService iActivityService;

    private static final long UPLOAD_MAX_SIZE = 30 * 1024 * 1024;

    private static final List<String> UPLOAD_CONTENT_TYPES = new ArrayList<>();
    static {
        UPLOAD_CONTENT_TYPES.add("image/jpeg");
        UPLOAD_CONTENT_TYPES.add("image/jpg");
        UPLOAD_CONTENT_TYPES.add("image/png");
        UPLOAD_CONTENT_TYPES.add("image/bmp");
        UPLOAD_CONTENT_TYPES.add("image/gif");
    }

    private final static String ROOT_PATH = System.getProperty("user.home") + File.separator;

    //    @Value("${server.port}")
//    private String port;

//    /**
//     * 获取当前ip地址
//     *
//     * @return 返回ip地址
//     */
//    public String getIp() {
//        InetAddress localhost = null;
//        try {
//            localhost = Inet4Address.getLocalHost();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return localhost.getHostAddress();
//    }

    @PostMapping("upload")
    public ResponseResult<List<GuestPhoto>> upload(Integer activityId,@RequestParam("file")MultipartFile[] files, HttpServletResponse response, HttpSession session) {
        Integer uid = getUidFromSession(session);
        iUserListService.getByUid(uid);
        Activity activity =iActivityService.findActivityById(activityId);
        String activityName =activity.getActivityName();

        log.info("上传多个文件");
        StringBuilder builder =new StringBuilder();

        String strongPath =ROOT_PATH+activityId+File.separator+"img"+File.separator;

        long size;

        try {
            for (MultipartFile file : files) {
                size=file.getSize();
                if (file.isEmpty()){
                    throw new FlieEmptyException("文件为空");
                }
                if (size>UPLOAD_MAX_SIZE){
                    throw new FileSizeException("文件太大");
                }
                String contentType =file.getContentType();
                if (!UPLOAD_CONTENT_TYPES.contains(contentType)){
                    throw new FileStateException("文件类型错误");
                }
                String photoAddress=file.getOriginalFilename();
                int beginIndex = photoAddress.lastIndexOf(".");
                String suffix = photoAddress.substring(beginIndex);
                String fileName = System.currentTimeMillis() + suffix;
                String photoPath =strongPath+fileName;
                try {
                    Streams.copy(file.getInputStream(),new FileOutputStream(photoPath),true);
                }catch (IllegalStateException e) {
                    e.printStackTrace();
                    throw new FileStateException("照片无法访问！");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new FileUploadException("上传异常");
                }
                iGuestPhotoService.savePhoto(uid,activityName,photoPath);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<GuestPhoto> photoList = iGuestPhotoService.findPhotoByActivityName(activityName);
        return new ResponseResult<>(SUCCESS,photoList);
    }

    @PostMapping("/")
    public ResponseResult<List<GuestPhoto>> photo(String activityName){
        return  new ResponseResult<>(SUCCESS,iGuestPhotoService.findPhotoByActivityName(activityName));
    }

    @PostMapping("remove")
    public ResponseResult<Void> delete (Integer pid,HttpSession session){
        Integer uid = getUidFromSession(session);
        iGuestPhotoService.deletePhotoByPid(uid,pid);
        return new ResponseResult<>(SUCCESS);
    }
}
