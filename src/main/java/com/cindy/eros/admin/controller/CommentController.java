package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.impl.SecretServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public BaseResponse addComment(@RequestParam("content") String content){


        return BaseResponse.success("");
    }
}
