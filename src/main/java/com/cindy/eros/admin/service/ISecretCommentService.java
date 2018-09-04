package com.cindy.eros.admin.service;

import com.cindy.eros.admin.model.SecretComment;

import java.util.List;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-14 12:17
 **/

public interface ISecretCommentService {

    public void addComment(SecretComment comment) throws Exception;

    public void updateComment(SecretComment comment);

    public Boolean checkUserId(Integer id);

    public List<SecretComment> selectCommentLimit(Integer arg0,Integer arg1,Byte status);
}
