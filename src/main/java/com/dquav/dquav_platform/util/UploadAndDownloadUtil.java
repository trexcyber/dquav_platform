package com.dquav.dquav_platform.util;

import com.dquav.dquav_platform.controller.ex.FileSizeException;
import com.dquav.dquav_platform.controller.ex.FileStateException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.FileEntity;
import com.dquav.dquav_platform.entity.GuestPhoto;
import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.service.IGuestPhotoService;
import com.dquav.dquav_platform.util.ex.FileException;
import com.dquav.dquav_platform.util.ex.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/7/5 - 15:30
 */
@Component
public class UploadAndDownloadUtil {


    @Resource
    IGuestPhotoService iGuestPhotoService;

    private static final long UPLOAD_MAX_SIZE = 30 * 1024 * 1024;

//    private final static String ROOT_PATH = System.getProperty("user.home") + File.separator;

    public FileEntity uploadName(MultipartFile multipartFile,String path){
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || "".equals(originalFilename)) {
            throw new FlieEmptyException("文件名为空");
        }
        int beginIndex = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(beginIndex);
        String fileName = System.currentTimeMillis() + suffix;
        String strongPath = path + fileName;
        try {
            Streams.copy(multipartFile.getInputStream(), new FileOutputStream(strongPath), true);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileException("文档无法访问！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException ("上传异常");
        }
        FileEntity file = new FileEntity();
        file.setFileName(fileName);
        file.setOriginalFilename(originalFilename);
        file.setStrongPath(strongPath);
        return file;
    }


    public void download( String entityName,Object object,String path,HttpServletResponse response){
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
//            取出输出流
            outputStream = response.getOutputStream();
//            清空输出流
            response.reset();
            if (object instanceof PhotoPackage) {
                response.setContentType("application/x-zip-compressed; charset=GBK");
            }else if (object instanceof Doc){
                response.setContentType("application/x-download; charset=GBK");
            }
            System.out.println("2222"+entityName);
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(entityName.getBytes(StandardCharsets.UTF_8),
                            StandardCharsets.ISO_8859_1));
            System.out.println("333"+entityName);
//            读取流
            File file = new File(path);
            inputStream = new FileInputStream(file);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String fileName(MultipartFile file){
        String address=file.getOriginalFilename();

        if (address == null || "".equals(address)) {
            throw new FlieEmptyException("文件名为空");
        }
        int beginIndex = address.lastIndexOf(".");
        String suffix = address.substring(beginIndex);

        return System.currentTimeMillis() + suffix;
    }


    private static final List<String> UPLOAD_CONTENT_TYPES = new ArrayList<>();
    static {
        UPLOAD_CONTENT_TYPES.add("image/jpeg");
        UPLOAD_CONTENT_TYPES.add("image/jpg");
        UPLOAD_CONTENT_TYPES.add("image/png");
        UPLOAD_CONTENT_TYPES.add("image/bmp");
        UPLOAD_CONTENT_TYPES.add("image/gif");
    }


    public void uploadPhoto(Integer uid, String activityName, MultipartFile[] files,String path){
        StringBuilder builder =new StringBuilder();
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
                String photoPath =path+fileName(file);
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

    }



}
