package com.web.springbootpro.controller.api;

import com.web.springbootpro.domain.ResponseDto;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@Slf4j
@RestController
    public class UserApiController {

        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

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

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
            System.out.println("회원정보 수정 함수 호출");
            log.info("회원정보 수정 데이터 = {}",user);
            userService.userUpdate(user);

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
