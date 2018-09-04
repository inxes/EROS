package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.SecretComment;
import com.cindy.eros.admin.service.impl.SecretCommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: eros
 * @description: 秘密评论管理
 * @author: cindy
 * @create: 2018-08-07 20:20
 **/

@RequestMapping("/comment")
@RestController
public class CommentController {

    @PostMapping("/add")
    public BaseResponse addComment(@RequestParam("content") String content,
                                   @RequestParam("id") Integer id,
                                   HttpSession session,
                                   SecretCommentServiceImp commentService){
        Date date = new Date();
        SecretComment comment = new SecretComment();
        comment.setContent(content);
        comment.setPrincipalId(id);
        comment.setType(commentService.COMMENT_TYPE);
        comment.setStatus(commentService.VALID_STATUS);
        comment.setUserId(Integer.parseInt(session.getAttribute("uid").toString()));
        comment.setCreateTime(date);
        comment.setUpdateTime(date);
        try {
            commentService.addComment(comment);
            BaseResponse.success("");
        }catch(Exception e){
            return BaseResponse.failure(1101,BaseResponse.codeMap.get(1101));
        }

        return BaseResponse.success("");
    }

    @PostMapping("/delete")
    public BaseResponse deleteComment(@RequestParam("id") Integer id,
                                      @RequestParam(value = "idadmin") Boolean isadmin,
                                      SecretCommentServiceImp commentService){
        if (!isadmin){
            if (!commentService.checkUserId(id)){
                //确认用户只能删除自己的评论
                return BaseResponse.failure(1102,BaseResponse.codeMap.get(1102));
            }else{
                //非管理员用户删除评论
                SecretComment comment = new SecretComment();
                Date date = new Date();
                comment.setId(id);
                comment.setUpdateTime(date);
                comment.setStatus(commentService.DELETE_STATUS);
                commentService.updateComment(comment);
                return BaseResponse.success("");
            }
        }else{
            return BaseResponse.failure(1103,BaseResponse.codeMap.get(1103));
        }
    }

    @PostMapping("/list")
    public BaseResponse list(SecretCommentServiceImp commentService){
        return BaseResponse.success(commentService.selectCommentLimit(1,10,Byte.parseByte("0")));
    }
}
