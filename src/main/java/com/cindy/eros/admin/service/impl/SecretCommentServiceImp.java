package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.dao.SecretCommentMapper;
import com.cindy.eros.admin.model.SecretComment;
import com.cindy.eros.admin.service.ISecretCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-14 12:17
 **/

@Service
public class SecretCommentServiceImp implements ISecretCommentService {
    @Autowired
    private SecretCommentMapper mapper;

    public Byte DELETE_STATUS = -1;

    public Byte COMMENT_TYPE = 0;

    public Byte VALID_STATUS = 0;


    @Override
    public void addComment(SecretComment comment) throws Exception{
        mapper.insert(comment);
    }

    @Override
    public void updateComment(SecretComment comment) {
        mapper.updateByPrimaryKey(comment);
    }

    @Override
    public Boolean checkUserId(Integer id) {
        SecretComment deleteComment = mapper.selectByPrimaryKey(id);
        Integer userid = deleteComment.getUserId();
        if(userid.equals(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<SecretComment> selectCommentLimit(Integer arg0,Integer arg1,Byte status) {
        Map map = new HashMap();
        map.put("arg0",arg0);
        map.put("arg1",arg1);
        map.put("arg2",status);
        return mapper.selectCommentLimit(map);
    }
}
