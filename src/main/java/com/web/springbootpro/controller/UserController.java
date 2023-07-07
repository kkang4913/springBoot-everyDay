package com.web.springbootpro.controller;

import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * /auth/   : 인증이 되지 않은 사용자도 출입이 가능한 경로
     */
    @GetMapping("/auth/joinForm")
    public String join(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String login(){
        return "user/loginForm";
    }





}
