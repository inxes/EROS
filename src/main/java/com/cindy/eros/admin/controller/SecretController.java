package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.impl.SecretServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public BaseResponse addSecret(@RequestParam("content") String content, @RequestParam(value = "img",required = false,defaultValue = "") MultipartFile img, @ModelAttribute("uid") Integer uid){
        Secret secret = new Secret();
        Date date = new Date();
        secret.setContent(content);
        secret.setCreateTime(date);
        secret.setUserId(uid);
        if(!img.isEmpty()){
            String path = "/home/cindy/文档/dada/eros/src/main/resources/static/images/";

            String filename = img.getOriginalFilename();
            //获取文件后缀
            String suffix = filename.substring(filename.lastIndexOf(".")+1);
            //
            String realname = img.hashCode()+"."+suffix;
            try{
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path+realname)));
                stream.write(img.getBytes());
                stream.flush();
                stream.close();
                Map map = new HashMap();
                map.put("filename",realname);
                secret.setImg(realname);
            }catch (Exception e){
                return BaseResponse.failure(1000,BaseResponse.codeMap.get(1000));
            }


        }

        try{
            secretServiceImp.saveSecret(secret);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return BaseResponse.success("");
    }

}
