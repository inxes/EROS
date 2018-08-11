package com.cindy.eros.admin.service;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-07 20:23
 **/

public interface ISecretService {

    public void saveSecret(Secret secret) throws Exception;

    public BaseResponse uploadSecretImg(MultipartFile file);

    public List selectSecretLimit(Integer start,Integer end);
}
