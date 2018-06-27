package com.test.demo;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/Comment")
public class CommentController {

    @RequestMapping(value = {"/comment/{id}","/getComment/{id}"},method = RequestMethod.GET)
    public String getComment(@PathVariable("id") Integer id, @RequestParam(value = "name",required = false,defaultValue = "Cindy") String name){
        return "This is "+ id +"'s Comment!"+"Name is"+name;
//        return "index"; //模版引擎
    }
}
