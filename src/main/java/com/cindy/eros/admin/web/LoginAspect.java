package com.cindy.eros.admin.web;

import com.auth0.jwt.interfaces.Claim;
import com.cindy.eros.admin.dao.AdminUserMapper;
import com.cindy.eros.admin.model.AdminUser;
import com.cindy.eros.admin.model.BaseResponse;
import com.cindy.eros.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-14 14:47
 **/

@Component
@Aspect
public class LoginAspect {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Pointcut("execution(public * com.cindy.eros.admin.controller.*.*(..))")
    public void loginLog(){
    }

    @Pointcut("within(com.cindy.eros.admin.controller.*)")
    public void withTest(){
    }

    @Before("withTest())")
    public void withVut(){
        System.out.println(123);
    }

    @Before("loginLog()")
    public void loginBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpSession session = request.getSession();

        String auth = request.getHeader("Authorization");

        JwtUtil util = new JwtUtil();
        try{
            Map<String,Claim> map = util.verifyJwt(auth);

            if(!map.isEmpty()){
                AdminUser admin = new AdminUser();
                Date date = new Date();
                Integer uid = map.get("userid").asInt();
                session.setAttribute("uid",uid);
                admin.setId(uid);
                admin.setUpdateTime(date);
                adminUserMapper.updateByPrimaryKeySelective(admin);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
