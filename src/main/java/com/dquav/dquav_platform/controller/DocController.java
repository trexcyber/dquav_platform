package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.controller.ex.FileSizeException;
import com.dquav.dquav_platform.controller.ex.FlieEmptyException;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.FileEntity;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IDocService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.util.ResponseResult;
import com.dquav.dquav_platform.util.UploadAndDownloadUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
     * 获取活动项目下的文档列表
     *
     * @param activityId 活动id
     * @return 返回文档列表
     */
    @GetMapping("/")
    public ResponseResult<List<Doc>> docListByActivity(@RequestParam("activity_id") Integer activityId) {
        List<Doc> docList = iDocService.findDocListByActivityId(activityId);
        return new ResponseResult<>(SUCCESS, docList);
    }

    /**
     * 删除文档
     *
     * @param activityId 项目id
     * @param docName    文档名
     * @param session    用户名
     * @return 删除成功后的状态
     */
    @PostMapping("remove")
    public ResponseResult<Void> deleteDoc(@RequestParam("activityid") Integer activityId,
                                          @RequestParam("doc_name") String docName,
                                          HttpSession session) {
        String username = getUsernameFromSession(session);
        Doc doc = iDocService.findDocByActivityIdAndDocName(activityId, docName);
        iDocService.removeDoc(username, docName);
        return new ResponseResult<>(SUCCESS);
    }

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
            , @RequestParam("file") MultipartFile[] multipartFiles, HttpSession session){


//        从session中获取uid
        Integer uid = getUidFromSession(session);
//        判断userList中是否存在此用户
        UserList userList = iUserListService.getByUid(uid);
        String username = userList.getUsername();
//        判断活动项目是否创建
        iActivityService.findActivityById(activityId);
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

            UploadAndDownloadUtil uploadAndDownloadUtil =new UploadAndDownloadUtil();
            FileEntity fileEntity = uploadAndDownloadUtil.uploadName(multipartFile);

//            执行保存信息
            doc.setActivityId(activityId);
            doc.setDocName(fileEntity.getOriginalFilename());
            doc.setDocSite(fileEntity.getStrongPath());
            doc.setIsDelete(fileEntity.getFileName());
            iDocService.saveDoc(username, doc);
        }
        return new ResponseResult<>(SUCCESS);
    }

    /**
     * 下载文档
     *
     * @param activityId 活动id
     * @param docName    文件名
     * @param response   后端响应
     * @param request    后端请求
     * @return 返回需下载文件
     */
    @RequestMapping("download")
    public ResponseResult<Void> downloadFile(@RequestParam("activity_id") Integer activityId, @RequestParam(
            "doc_name") String docName, final HttpServletResponse response, final HttpServletRequest request) {
        Doc doc = iDocService.findDocByActivityIdAndDocName(activityId, docName);

        //        获取文件存储路径
        String fileName = doc.getIsDelete();

        UploadAndDownloadUtil uploadAndDownloadUtil = new UploadAndDownloadUtil();
        uploadAndDownloadUtil.download(fileName,docName,doc,response);

        return new ResponseResult<>(SUCCESS);
    }
}
