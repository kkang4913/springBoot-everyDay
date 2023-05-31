package com.web.springbootpro;

import com.web.springbootpro.domain.MemberVo;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    //http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(@RequestParam Long id, @RequestParam String username
    , MemberVo m){
        return "get 요청" + id + username + m.getId()+ m.getUsername()+m.getPassword()+m.getEmail();
    }

    //http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest(MemberVo m, @RequestBody String text){
        return "post 요청"+"form 형식" +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail()
                + "text 또는 JSON 방식 : " +text ;
    }

    //http://localhost:8080/http/put
    @GetMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    //http://localhost:8080/http/delete
    @GetMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
