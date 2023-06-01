package com.web.springbootpro.service;


import com.web.springbootpro.model.User;
import com.web.springbootpro.mapper.UserTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserTestMapper mapper;

    public void userJoin(User user) {
        mapper.userJoin(user);
    }

    public User findById(Long id) {
       User user = mapper.findById(id);
        return user;
    }

    public List<User> findAll() {
        return mapper.findAll();
    }

    public List<User> getUserList(int start,int end) {
        return mapper.getUserList(start,end);
    }

    public int getTotalUsers() {
        return mapper.getTotalUsers();
    }
}
