package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.FileSizeException;
import com.dquav.dquav_platform.controller.ex.FileStateException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IDocService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.service.ex.ActivityNotFoundException;
import com.dquav.dquav_platform.service.ex.UserNotFoundException;
import com.dquav.dquav_platform.util.ResponseResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
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
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.stream.Stream;

/**
 * @author TrEx
 * @date 2021/5/8 - 15:03
 */
@RestController
@RequestMapping("doc")
public class DocController extends BaseController {

    @Resource
    IDocService iDocService;
    @Resource
    IActivityService iActivityService;
    @Resource
    IUserListService iUserListService;

    private static final long UPLOAD_MAX_SIZE = 30 * 1024 * 1024;

    private final static String ROOT_PATH = System.getProperty("user.home") + File.separator;

    /**
     * 上传文档
     *
     * @param activityId     活动名
     * @param docName        文件名
     * @param multipartFiles 文件数据
     * @param session        用户id
     * @return 上传成后的文档列表路径
     * @throws IOException 流异常
     */
    @PostMapping("upload")
    public ResponseResult<Void> upload(@RequestParam("activity_id") Integer activityId,
                                       @RequestParam("doc_name") String docName
            , @RequestParam("file") MultipartFile[] multipartFiles, HttpSession session) throws IOException {


//        从session中获取uid
        Integer uid = getUidFromSession(session);
//        判断userList中是否存在此用户
        UserList userList = iUserListService.getByUid(uid);
        String username = userList.getUsername();
//        判断活动项目是否创建
        iActivityService.getActivityById(activityId);
//        创建一个doc对象
        Doc doc = new Doc();

        long size;
        String path = ROOT_PATH + activityId + File.separator;
        File fileDir = new File(path);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            size = multipartFile.getSize();
            if (multipartFile.isEmpty()) {
                throw new FlieEmptyException("上传文件为空");
            }
            if (size > UPLOAD_MAX_SIZE) {
                throw new FileSizeException("上传文件有大小超过" + UPLOAD_MAX_SIZE / 1024 + "KB");
            }

            String originalFilename = multipartFile.getOriginalFilename();
            int beginIndex = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(beginIndex);
            String fileName = System.currentTimeMillis() + suffix;
            String strongPath = ROOT_PATH + fileName;
            try {
                Streams.copy(multipartFile.getInputStream(), new FileOutputStream(strongPath), true);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                throw new FileStateException("文档无法访问！");
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileUploadException("上传异常");
            }
//            执行保存信息
            doc.setActivityId(activityId);
            doc.setDocName(originalFilename);
            doc.setDocSite(strongPath);
            doc.setIsDelete(fileName);
            iDocService.saveDoc(username, doc);
        }
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("download")
    public ResponseResult<Object> downloadFile(@RequestParam("activity_id") Integer activityId, @RequestParam(
            "doc_name") String docName, final HttpServletResponse response, final HttpServletRequest request) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        Doc doc = iDocService.findDocByName(docName);
        String fileName = doc.getIsDelete();
        try {
//            取出输出流
            outputStream = response.getOutputStream();
//            清空输出流
            response.reset();
            response.setContentType("application/x-download; charset=GBK");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(docName.getBytes(StandardCharsets.UTF_8),
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
        return new ResponseResult<>(SUCCESS);
    }
}
