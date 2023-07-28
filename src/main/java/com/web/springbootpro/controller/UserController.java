package com.web.springbootpro.controller;

import com.web.springbootpro.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * /auth/   : 인증이 되지 않은 사용자도 출입이 가능한 경로
     */
    @GetMapping("/auth/joinForm")
    public String join(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String login(@RequestParam(value = "error",required = false) String error,
                        @RequestParam(value = "exception",required = false) String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }


    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        userService.kakaoLogin(code);

        return "redirect:/";
    }
}
