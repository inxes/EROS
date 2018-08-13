package com.cindy.eros.admin.controller;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.cindy.eros.admin.model.BaseResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;

@RestController
public class SampleController {

    @Value("${ErosConfig.baiduAI.APP_ID}")
    private String APP_ID;

    @Value("${ErosConfig.baiduAI.API_KEY}")
    private String API_KEY;

    @Value("${ErosConfig.baiduAI.SECRET_KEY}")
    private String SECRET_KEY;

        @PostMapping("/upload")
        @CrossOrigin(origins = "http://localhost:8080")
        public BaseResponse upload(@RequestParam("file")MultipartFile file){
            String path = "/home/cindy/文档/dada/eros/src/main/resources/static/images/";
            BaseResponse br = new BaseResponse();
            HashMap<String,Object> data = new HashMap<String, Object>();
            br.setData(data);
            if (!file.isEmpty()){
                try {
                    String filename = file.getOriginalFilename();
                    System.out.println(filename);
                    String suffix = filename.substring(filename.lastIndexOf(".")+1);
                    System.out.println(suffix);
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path+file.hashCode()+'.'+suffix)));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    String info = getImageInfo(path+file.hashCode()+'.'+suffix);
                    return BaseResponse.success(info);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                    return BaseResponse.failure(101,"上传文件失败！"+e.getMessage());
                }catch (IOException e){
                    return BaseResponse.failure(103,"服务器接收文件失败！"+e.getMessage());
                }

            }else{
                return BaseResponse.failure(102,"上传文件不能为空！");
            }
        }


    private String getImageInfo(String path) {
        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(20000);
        client.setSocketTimeoutInMillis(600000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//            client.setHttpProx`y("proxy_host", proxy_port);  // 设置http代理
//            client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        /*
        可选：设置log4j日志输出格式，若不设置，则使用默认配置
        也可以直接通过jvm启动参数设置此环境变量
        */

        System.setProperty("aip.log4j.conf", "/home/cindy/文档/dada/eros/src/main/resources/log4j.properties");

        // 调用接口
        JSONObject res = client.advancedGeneral(path, new HashMap<String, String>());
        return res.toString();
    }
}
