package com.web.springbootpro.controller.api;

import com.web.springbootpro.domain.ResponseDto;
import com.web.springbootpro.dto.UserRequestDto;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import com.web.springbootpro.validator.CheckUserEmailValidator;
import com.web.springbootpro.validator.CheckUserNicknameValidator;
import com.web.springbootpro.validator.CheckUsernameValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
    public class UserApiController {

        private final UserService userService;
        private final AuthenticationManager authenticationManager;
        private final CheckUserNicknameValidator checkUserNicknameValidator;
        private final CheckUsernameValidator checkUsernameValidator;
        private final CheckUserEmailValidator checkUserEmailValidator;


        @InitBinder
        public void validatorBinder(WebDataBinder binder){
            binder.addValidators(checkUsernameValidator);
            binder.addValidators(checkUserNicknameValidator);
            binder.addValidators(checkUserEmailValidator);

        }


        @PostMapping("/auth/api/joinProc")
        public ResponseDto<?> join(@Valid @RequestBody UserRequestDto userDto, BindingResult bindingResult){
            System.out.println("USER 회원가입 함수 호출");

            if (bindingResult.hasErrors()){
                Map<String,String> validatorResult = userService.validateHandling(bindingResult);

                return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
            }

            userService.userJoin(userDto);

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
