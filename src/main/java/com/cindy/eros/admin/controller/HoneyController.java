package com.cindy.eros.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.service.impl.HoneyService;
import com.cindy.eros.util.UploadUtil;
import com.qcloud.image.ImageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-13 15:28
 **/


@RestController
@RequestMapping("/honey")
public class HoneyController {

    @Value("${ErosConfig.tencentAI.APP_ID}")
    private String APP_ID_T;

    @Value("${ErosConfig.tencentAI.SECRET_ID}")
    private String SECRET_ID_T;

    @Value("${ErosConfig.tencnetAI.SECRET_KEY}")
    private String SECRET_KEY_T;

    @Value("${ErosConfig.tencentAI.BUCKETNAME}")
    private String BUCKETNAME;

    @Value("${ErosConfig.ImagePath.Honey}")
    private String HONEYPATH;

    @Autowired
    private HoneyService honeyService;

    private String PATH = "/home/cindy/文档/dada/eros/src/main/resources/static/honey_img/";

    @PostMapping("/scanImg")
    public BaseResponse uploadImg(@RequestParam("path") String path){
        ImageClient client = new ImageClient(APP_ID_T,SECRET_ID_T,SECRET_KEY_T);
        String response = honeyService.uploadImg(client, BUCKETNAME,PATH+path);

        //处理返回的数据，得到词频
        List list = honeyService.getSegmente(JSONObject.parseObject(response));
        System.out.println(JSONObject.parse(response));
        return BaseResponse.success(list);
    }

    @PostMapping("/upload")
    public BaseResponse upload(@RequestParam("file") MultipartFile file){
        UploadUtil uploadUtil = new UploadUtil();
        return uploadUtil.upload(file,PATH);
    }

}
