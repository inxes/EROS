package com.cindy.eros.admin.dao;

import com.cindy.eros.admin.model.Secret;

import java.util.List;

public interface SecretMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Secret record);

    int insertSelective(Secret record);

    Secret selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Secret record);

    int updateByPrimaryKey(Secret record);

    List<Secret> selectSecretLimit(Integer arg0, Integer arg1);

}