package com.org.asean.javase.web.service;

import com.org.asean.javase.web.dao.UserMapper;
import com.org.asean.javase.web.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id){

        User user = userMapper.selectByPrimaryKey(id);

        return user;

    }

    public void saveUser() {

        User user = new User();
        user.setName("李明");
        user.setCreatedate(new Date());
        user.setEmail("lucy@163.com");
        user.setDid(Long.valueOf("12121"));
        user.setSex(1);
        userMapper.insert(user);
    }
}
