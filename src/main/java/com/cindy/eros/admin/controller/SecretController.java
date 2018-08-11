package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.impl.SecretServiceImp;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-07 17:15
 **/

@RestController
@RequestMapping("/secret")
public class SecretController {

    @Autowired
    private SecretServiceImp secretServiceImp;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8080")
    public BaseResponse addSecret(@RequestParam("content") String content,
                                  @RequestParam(value = "img",required = false,defaultValue = "") String img,
                                  HttpSession session){
        System.out.println("session_id:"+session.getId());
        System.out.println("uid"+session.getAttribute("uid"));
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        System.out.println("uid:"+uid);
        Secret secret = new Secret();
        Date date = new Date();
        secret.setContent(content);
        secret.setCreateTime(date);
        secret.setUserId(uid);
        secret.setUpdateTime(date);
        if(!img.isEmpty()){
            secret.setImg(img);
        }

        try{
            secretServiceImp.saveSecret(secret);
            return BaseResponse.success("发表成功！");
        }catch(Exception e){
            System.out.println(e.getMessage());
            return BaseResponse.failure(1100,BaseResponse.codeMap.get(1100));
        }
    }

    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile file){
        return secretServiceImp.uploadSecretImg(file);
    }

    @PostMapping("/list")
    public BaseResponse list(){
        return BaseResponse.success(secretServiceImp.selectSecretLimit(1,10));
    }
}
