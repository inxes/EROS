package com.cindy.eros.admin.service;

import javax.servlet.http.HttpSession;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-09-04 18:41
 **/

public interface ISessionDataService {

    public String getUserId(HttpSession session);
}
