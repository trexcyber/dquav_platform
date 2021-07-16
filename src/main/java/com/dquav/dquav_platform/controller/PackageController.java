package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.FileSizeException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.FileEntity;
import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IPackageService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.ResponseResult;
import com.dquav.dquav_platform.util.UploadAndDownloadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/7/2 - 17:51
 */
@RestController
@RequestMapping("package")
public class PackageController extends BaseController {

    @Resource
    IPackageService iPackageService;
    @Resource
    IUserListService iUserListService;
    @Resource
    IActivityService iActivityService;
    @Resource
    UploadAndDownloadUtil uploadAndDownloadUtil;

    private static final long UPLOAD_MAX_SIZE = 30 * 1024 * 1024;

    private final static String ROOT_PATH = System.getProperty("user.home") + File.separator;

    @PostMapping("/")
    public ResponseResult<List<PhotoPackage>> allPackage(@RequestParam("activity_name") String activityName) {
        List<PhotoPackage> data = iPackageService.findPackageByActivityName(activityName);
        return new ResponseResult<>(SUCCESS, data);
    }

    @PostMapping("this")
    public ResponseResult<PhotoPackage> packageResponseResult(@RequestParam("package_name") String photoPackageName) {
        return new ResponseResult<>(SUCCESS, iPackageService.findPackageByPhotoPackageName(photoPackageName));
    }

    @PostMapping("remove")
    public ResponseResult<Void> removePackage(@RequestParam("package_name") String photoPackageName,
                                              HttpSession session) {
        Integer uid = getUidFromSession(session);
        iPackageService.removePackageByPhotoPackageName(uid, photoPackageName);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("remove-all")
    public ResponseResult<Void> removeByActivity(@RequestParam("activity_name") String activityName,
                                                 HttpSession session) {
        Integer uid = getUidFromSession(session);
        iPackageService.removeAllPackageByActivityId(uid, activityName);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("upload")
    public ResponseResult<Void> upload(@RequestParam("activity_name") String activityName,
                                       @RequestParam("file") MultipartFile[] multipartFiles,
                                       HttpSession session) {
        Integer uid = getUidFromSession(session);
        UserList userList = iUserListService.getByUid(uid);
        String username = userList.getUsername();

        Activity activity = iActivityService.getActivity(activityName);
        Integer activityId = activity.getActivityId();

        PhotoPackage photoPackage = new PhotoPackage();

        long size;
        String path = ROOT_PATH + activityId + File.separator + "package" + File.separator;
        File fileDir = new File(path);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            size = multipartFile.getSize();
            if (multipartFile.isEmpty()) {
                throw new FlieEmptyException("文件为空");
            }
            if (size > UPLOAD_MAX_SIZE) {
                throw new FileSizeException("文件超过：" + UPLOAD_MAX_SIZE + "KB");
            }
            FileEntity fileEntity = uploadAndDownloadUtil.uploadName(multipartFile, path);

            photoPackage.setActivityId(activityId);
            photoPackage.setPhotoPackageName(fileEntity.getOriginalFilename());
            photoPackage.setPhotoPackageSite(fileEntity.getStrongPath());
            photoPackage.setIsGuest(fileEntity.getFileName());
            iPackageService.savePackage(uid, photoPackage);

        }
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("down")
    public ResponseResult<Object> downPackage(@RequestParam("activity_name") String activityName, @RequestParam(
            "package_name") String photoPackageName, final HttpServletRequest request,
                                              final HttpServletResponse response) {
        Integer activityId = iActivityService.getActivity(activityName).getActivityId();

        PhotoPackage photoPackage = iPackageService.findPackageByActivityIdAndDocName(activityId, photoPackageName);
//        String fileName =photoPackage.getIsGuest();
        String filePath = photoPackage.getPhotoPackageSite();

        UploadAndDownloadUtil uploadAndDownloadUtil = new UploadAndDownloadUtil();
        uploadAndDownloadUtil.download(photoPackageName, photoPackage, filePath, response);
        return new ResponseResult<>(SUCCESS);
    }


}
