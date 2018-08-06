package com.cindy.eros.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * @program: eros
 * @description: 邮件发送工具
 * @author: cindy
 * @create: 2018-08-02 16:00
 **/

public class MailUtil{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.formMail.addr}")
    private String from;

    public void sendMile(String to,String subject,String content){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("690852990@qq.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try{
            mailSender.send(mailMessage);
            logger.info("邮件已发送");
        }catch (Exception e){
            logger.info("邮件发送失败:",e);
        }
    }
}
