package com.cindy.eros.admin.controller.photoWall;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.ImageWall;
import com.cindy.eros.admin.service.impl.ImageWallServiceImp;
import com.cindy.eros.admin.service.impl.SessionDataServiceImp;
import com.cindy.eros.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-09-04 16:15
 **/

@RestController
@RequestMapping("/wall")
public class PhotoWallShowController {

    @Value("${ErosConfig.ImagePath.ToWall}")
    private String toWallPath;

    @Autowired
    private ImageWallServiceImp imageWallServiceImp;

    @Autowired
    private SessionDataServiceImp sessionDataServiceImp;

    @PostMapping("/upload")
    public BaseResponse showPhoto(@RequestParam("file") MultipartFile file,
                                  HttpSession session){

        UploadUtil uploadUtil = new UploadUtil();
        BaseResponse upload = uploadUtil.upload(file,toWallPath);
        String path = upload.getMessage();
        Date date = new Date();
        ImageWall imageWall = new ImageWall();
        imageWall.setImg(path);
        imageWall.setUserId(Integer.parseInt(sessionDataServiceImp.getUserId(session)));
        imageWall.setValid(imageWallServiceImp.VALID_STATUS);
        imageWall.setCreateTime(date);
        imageWall.setUpdateTime(date);
        try{
            imageWallServiceImp.saveImageWall(imageWall);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return BaseResponse.success("");
    }
}
