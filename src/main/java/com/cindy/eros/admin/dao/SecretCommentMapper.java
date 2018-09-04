package com.cindy.eros.admin.dao;

import com.cindy.eros.admin.model.SecretComment;

import java.util.List;
import java.util.Map;

public interface SecretCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretComment record);

    int insertSelective(SecretComment record);

    SecretComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretComment record);

    int updateByPrimaryKey(SecretComment record);

    List<SecretComment> selectCommentLimit(Map map);
}