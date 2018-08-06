package com.cindy.eros.admin.service;

import com.cindy.eros.admin.model.AdminUser;

import java.util.List;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-07-30 18:21
 **/

public interface IAdminUserService {

    public void saveUser(AdminUser user) throws Exception;

    public void updateUser(AdminUser user);

    public void deleteUser(String UserId);

    public AdminUser queryUserById(String userId);

    public List<AdminUser> queryUserList(AdminUser user);

    public Integer saveUserReturnId(AdminUser user);

    public AdminUser selectByUsername(String username);

}
