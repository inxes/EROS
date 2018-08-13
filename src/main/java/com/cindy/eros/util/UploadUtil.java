package com.cindy.eros.util;

import com.cindy.eros.admin.model.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: eros
 * @description: 上传文件工具
 * @author: cindy
 * @create: 2018-08-10 11:00
 **/

public class UploadUtil {

    public BaseResponse upload(MultipartFile file, String path){
        if(!file.isEmpty()){
            //获取初始文件名
            String filename = file.getOriginalFilename();
            //获取文件名后缀
            String suffix = filename.substring(filename.lastIndexOf(".")+1);
            try{
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path+file.hashCode()+"."+suffix)));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
                System.out.println(path+file.hashCode()+"."+suffix);
                return BaseResponse.success(file.hashCode()+"."+suffix);
            }catch (Exception e){
                System.out.println("上传文件失败！"+e.getMessage());
                return BaseResponse.failure(1000,BaseResponse.codeMap.get(1000));
            }

        }else{
            return BaseResponse.failure(1001,BaseResponse.codeMap.get(1001));
        }

    }
}
