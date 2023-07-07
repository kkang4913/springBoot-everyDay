package com.web.springbootpro.controller.api;

import com.web.springbootpro.domain.ResponseDto;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@Slf4j
@RestController
    public class UserApiController {

        @Autowired
        private UserService userService;

        @PostMapping("/auth/api/joinProc")
        public ResponseDto<Integer> save(@RequestBody User user){
            System.out.println("UserApiController = save 호출");
            userService.userJoin(user);

            return  new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }

        @PostMapping("/auth/loginProc")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController = login 함수 호출");
        log.info("user={}",user);
        User principal = userService.userLogin(user);   //principal = 접근주체

        if (principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
