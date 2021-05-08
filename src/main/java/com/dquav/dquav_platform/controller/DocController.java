package com.dquav.dquav_platform.controller;

import com.dquav.dquav_platform.entity.BaseActivity;
import com.dquav.dquav_platform.service.IDocService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author TrEx
 * @date 2021/5/8 - 15:03
 */
@RestController
@RequestMapping("doc")
public class DocController extends BaseController {

    @Resource
    IDocService iDocService;

    @PostMapping("upload")
    public String upload(MultipartFile multipartFile,HttpSession session) throws IOException{
        return null;
    }
}
