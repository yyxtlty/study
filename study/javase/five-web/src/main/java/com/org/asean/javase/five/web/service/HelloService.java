package com.org.asean.javase.five.web.service;

import com.org.asean.javase.five.web.dao.UserMapper;
import com.org.asean.javase.five.web.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id){

       User user = userMapper.selectByPrimaryKey(id);

        return user;


    }
}
