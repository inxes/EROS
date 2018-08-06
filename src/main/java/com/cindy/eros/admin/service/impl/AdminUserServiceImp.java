package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.dao.AdminUserMapper;
import com.cindy.eros.admin.model.AdminUser;
import com.cindy.eros.admin.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: eros
 * @description: 实现类
 * @author: cindy
 * @create: 2018-07-30 18:26
 **/

@Service
public class AdminUserServiceImp implements IAdminUserService {
    @Autowired
    private  AdminUserMapper adminUserMapper;

    @Override
    public void saveUser(AdminUser user) throws Exception {
        adminUserMapper.insert(user);
    }

    @Override
    public void updateUser(AdminUser user) {
        adminUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUser(String UserId) {
        adminUserMapper.deleteByPrimaryKey(Integer.valueOf(UserId));
    }

    @Override
    public AdminUser queryUserById(String userId) {
        return null;
    }

    @Override
    public List<AdminUser> queryUserList(AdminUser user) {
        return null;
    }

    @Override
    public Integer saveUserReturnId(AdminUser user) {
        return adminUserMapper.insert(user);
    }

    @Override
    public AdminUser selectByUsername(String username) {
        return adminUserMapper.selectByUsername(username);
    }
}
