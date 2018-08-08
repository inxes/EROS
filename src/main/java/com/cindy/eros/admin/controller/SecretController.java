package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.dao.SecretMapper;
import com.cindy.eros.admin.model.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-07 17:15
 **/

@RestController
@RequestMapping("secret")
@CrossOrigin(origins = "http://localhost:8080")
public class SecretController {

    @PostMapping("/add")
    public BaseResponse addSecret(@RequestParam("content") String content,@RequestParam("user_id") Integer user_id){

        return BaseResponse.success("");
    }

}
