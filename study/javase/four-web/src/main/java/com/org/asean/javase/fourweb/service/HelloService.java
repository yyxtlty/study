package com.org.asean.javase.fourweb.service;

import com.org.asean.javase.fourweb.dao.UserMapper;
import com.org.asean.javase.fourweb.domain.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
