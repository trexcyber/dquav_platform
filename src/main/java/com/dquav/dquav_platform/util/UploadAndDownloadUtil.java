package com.dquav.dquav_platform.util;

import com.dquav.dquav_platform.controller.ex.FileStateException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.FileEntity;
import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.util.ex.FileException;
import com.dquav.dquav_platform.util.ex.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author TrEx
 * @date 2021/7/5 - 15:30
 */
public class UploadAndDownloadUtil {

    private static final long UPLOAD_MAX_SIZE = 30 * 1024 * 1024;

    private final static String ROOT_PATH = System.getProperty("user.home") + File.separator;

    public FileEntity uploadName(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || "".equals(originalFilename)) {
            throw new FlieEmptyException("文件名为空");
        }
        int beginIndex = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(beginIndex);
        String fileName = System.currentTimeMillis() + suffix;
        String strongPath = ROOT_PATH + fileName;
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


    public void download(String fileName, String entityName,Object object,HttpServletResponse response){
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
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(entityName.getBytes(StandardCharsets.UTF_8),
                            StandardCharsets.ISO_8859_1));
//            读取流
            File file = new File(ROOT_PATH + fileName);
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



}
