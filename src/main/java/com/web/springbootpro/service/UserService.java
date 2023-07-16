package com.web.springbootpro.service;


import com.web.springbootpro.model.User;
import com.web.springbootpro.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

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
        log.info("가입하는 유저 플랫폼 = {}", user.getOauth());
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


    public void userUpdate(User user) {
        System.out.println("userService[회원정보수정] = " + user);
        User persistance = mapper.findById(user.getId());
        if (persistance ==null) {
            throw  new IllegalArgumentException("회원찾기 실패");
        }
        String rawPassword = user.getPassword(); //원문 패스워드
        String encPassword = encoder.encode(rawPassword); //해쉬 패스워드

        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        mapper.userUpdate(persistance);
    }

    public User userSeach(String username) {
        User user = mapper.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }
}
