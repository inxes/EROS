package com.cindy.eros.admin.service.impl;

import com.cindy.eros.admin.dao.SecretMapper;
import com.cindy.eros.admin.model.Secret;
import com.cindy.eros.admin.service.ISecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-07 20:25
 **/

@Service
public class SecretServiceImp implements ISecretService {

    @Autowired
    private SecretMapper secretMapper;

    @Override
    public void saveSecret(Secret secret) throws Exception {
        secretMapper.insert(secret);
    }

}
