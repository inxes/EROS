package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.dao.ImageWallMapper;
import com.cindy.eros.admin.model.ImageWall;
import com.cindy.eros.admin.service.IImageWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-09-04 18:35
 **/

@Service
public class ImageWallServiceImp implements IImageWallService {

    public Byte VALID_STATUS = 1;

    @Autowired
    public ImageWallMapper mapper;

    @Override
    public void saveImageWall(ImageWall imageWall) throws Exception{
        mapper.insert(imageWall);
    }
}
