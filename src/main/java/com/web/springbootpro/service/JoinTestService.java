package com.web.springbootpro.service;


import com.web.springbootpro.model.User;
import com.web.springbootpro.mapper.JoinTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinTestService {
    @Autowired
    private JoinTestMapper mapper;

    public void userJoin(User user) {
        mapper.userJoin(user);
    }

    public User findById(Long id) {
       User user = mapper.findById(id);
        return user;
    }
}
