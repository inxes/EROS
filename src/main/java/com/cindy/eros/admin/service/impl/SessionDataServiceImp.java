package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.service.ISessionDataService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-09-04 18:41
 **/

@Service
public class SessionDataServiceImp implements ISessionDataService {

    @Override
    public String getUserId(HttpSession session) {
        return session.getAttribute("uid").toString();
    }
}
