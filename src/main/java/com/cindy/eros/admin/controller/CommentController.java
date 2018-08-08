package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.ISecretService;
import com.cindy.eros.admin.service.impl.SecretServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: eros
 * @description: 秘密评论管理
 * @author: cindy
 * @create: 2018-08-07 20:20
 **/

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private SecretServiceImp secretServiceImp;

    @PostMapping("/add")
    public BaseResponse addComment(@RequestParam("content") String content, HttpServletRequest request){
        Secret secret = new Secret();
        Date date = new Date();
        secret.setContent(content);
        secret.setCreateTime(date);
        secret.setUserId(Integer.parseInt(request.getSession().getAttribute("uid").toString()));
        try{
            secretServiceImp.saveSecret(secret);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return BaseResponse.success("");
    }
}
