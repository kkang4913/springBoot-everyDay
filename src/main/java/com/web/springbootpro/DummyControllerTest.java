package com.web.springbootpro;

import com.web.springbootpro.domain.Page;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
public class DummyControllerTest {

    @Autowired
    private UserService userService;

    //{} = 주소로 파라미터를 전달 받을 수 있다.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id){
        User user =userService.findById(id);
        return user;
    }

    @GetMapping("/test")
    public String userViews(){
        return "home";
    }


    //모든 데이터 출력
    @GetMapping("/dummy/user")
    @ResponseBody
    public Page<User> getUserList(@RequestParam(defaultValue = "1")int page
                                ,@RequestParam(defaultValue = "5")int size){
        log.info("page={}",page);
        log.info("size={}",size);

        int start = (page -1 ) * size + 1;
        int end   = page * size;

        List<User> userList = userService.getUserList(start,end);
        int totalUsers = userService.getTotalUsers();
        log.info("totalUsers={}",totalUsers);

        int totalPages =(int) Math.ceil((double) totalUsers / size);
        log.info("totalPages={}",totalPages);

        return new Page<>(userList,page,totalPages);
    }

    //http://localhost:8000/boot/dummy/join
    @PostMapping("/dummy/join")
    public String join(String username, String password, String email
    , User user){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        userService.userJoin(user);

        return "회원가입이 완료되었습니다.";
    }

}
