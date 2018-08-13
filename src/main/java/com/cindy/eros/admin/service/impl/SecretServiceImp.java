package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.dao.SecretMapper;
import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.ISecretService;
import com.cindy.eros.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-07 20:25
 **/

@Service
public class SecretServiceImp implements ISecretService {

    @Autowired
    private SecretMapper secretMapper;

    @Override
    public void saveSecret(Secret secret) throws Exception {
        secretMapper.insert(secret);
    }

    @Override
    public BaseResponse uploadSecretImg(MultipartFile file) {
        String path = "/home/cindy/文档/dada/eros/src/main/resources/static/secret_img/";
        UploadUtil uploadUtil = new UploadUtil();
        BaseResponse response = uploadUtil.upload(file,path);
        return response;
    }

    @Override
    public List selectSecretLimit(Integer start, Integer end) {
        return secretMapper.selectSecretLimit(start,end);
    }
}
