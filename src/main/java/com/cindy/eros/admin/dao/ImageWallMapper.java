package com.cindy.eros.admin.dao;

import com.cindy.eros.admin.model.ImageWall;

public interface ImageWallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageWall record);

    int insertSelective(ImageWall record);

    ImageWall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageWall record);

    int updateByPrimaryKey(ImageWall record);
}