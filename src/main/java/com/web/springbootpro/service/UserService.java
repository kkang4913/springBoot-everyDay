package com.web.springbootpro.service;


import com.web.springbootpro.model.User;
import com.web.springbootpro.mapper.UserTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserTestMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 회원가입
     * @param user
     */
    public void userJoin(User user) {
        String rawPassword = user.getPassword();             //원문 패스워드
        String encPassword = encoder.encode(rawPassword);    //해쉬 패스워드

        user.setPassword(encPassword);
        user.setRole("USER");

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


    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    public void deleteById(Long id) {
        mapper.deleteById(id);
    }

    public User userLogin(User user) {
        System.out.println("userService 실행 = " + user);
        mapper.userLogin(user);
        return user;
    }


}
