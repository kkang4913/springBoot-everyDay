package com.web.springbootpro;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.service.BoardService;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
public class HomeController {



    /**
     * http://localhost8000/ : context-path: /
     * http://localhost8000/
     * @return : home.jsp : 기본적인 메인 페이지
     */

    /**
     * 메인 페이지
     * @param principal
     * @return  : home.jsp
     */
    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalDetail principal){
        log.info("principal 정보 = {}" , principal);
        return "home";
    }


}
