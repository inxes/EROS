package com.cindy.eros.admin.controller;

import com.cindy.eros.admin.model.AdminUser;
import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.admin.service.impl.AdminUserServiceImp;
import com.cindy.eros.util.JwtUtil;
import com.cindy.eros.util.MailUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AdminUserServiceImp adminUserServiceImp;

    @PostMapping("/logon")
    @CrossOrigin(origins = "http://localhost:8080")
    public BaseResponse logon(@RequestParam("username") String username,@RequestParam("pwd") String pwd,@RequestParam("email") String email){
        if (adminUserServiceImp.selectByUsername(username) != null){
            return BaseResponse.failure(120,"注册失败！该账号已被使用");
        }
        AdminUser user = new AdminUser();
        Date date = new Date();
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(pwd,BCrypt.gensalt()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setCreateTime(date);

        MailUtil mailUtil = new MailUtil();
        mailUtil.sendMile(email,"【EROS】验证邮件","以下为您的验证码，验证码24小时内有效：");

        //生成token
        try{
            Integer id = adminUserServiceImp.saveUserReturnId(user);
            JwtUtil jwtUtil = new JwtUtil();
            String token = jwtUtil.createJwt(id);
            return BaseResponse.success(token);
        }catch (Exception e){
            return BaseResponse.failure(121,"注册失败！"+e.getMessage());
        }

    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:8080")
    public BaseResponse login(@RequestParam(value = "username",required = false,defaultValue = "") String username,
                              @RequestParam(value = "pwd",required = false,defaultValue = "") String pwd,
                              @RequestHeader(value = "Authorization",required = false,defaultValue = "") String auth){
        JwtUtil jwtUtil = new JwtUtil();
        System.out.println(auth.isEmpty());
        if (!auth.isEmpty()){
            Boolean verify = jwtUtil.verifyJwt(auth);
            //jwt校驗
            if(username.isEmpty() && pwd.isEmpty() && verify){
                return BaseResponse.success(auth);
            }

        }

        //數據庫校驗
        AdminUser user = adminUserServiceImp.selectByUsername(username);
        String password = user.getPassword();
        Boolean checkLogin = BCrypt.checkpw(pwd,password);

        //校验通过，发放token
        if(checkLogin){
            Integer id = user.getId();
            try{
                String token = jwtUtil.createJwt(id);
                return BaseResponse.success(token);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return BaseResponse.failure(123,"登陆失败！");
            }

        }else{
            return BaseResponse.failure(123,"登陆失败！");
        }
    }

    @PostMapping("/checkUsername")
    @CrossOrigin(origins = "http://localhost:8080")
    public BaseResponse checkUsername(@RequestParam("username") String username){
        AdminUser user = adminUserServiceImp.selectByUsername(username);
        if(user == null){
            return BaseResponse.success(false);
        }else{
            return BaseResponse.success(true);
        }

    }
}
