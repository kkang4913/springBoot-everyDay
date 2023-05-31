package com.web.springbootpro;

import com.web.springbootpro.model.User;
import com.web.springbootpro.service.JoinTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired
    JoinTestService joinService;

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id){
        User user =joinService.findById(id);

        return user;
    }



    //http://localhost:8000/boot/dummy/join
    @PostMapping("/dummy/join")
    public String join(String username, String password, String email
    , User user){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        joinService.userJoin(user);

        return "회원가입이 완료되었습니다.";
    }

}
