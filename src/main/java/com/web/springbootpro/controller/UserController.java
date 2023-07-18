package com.web.springbootpro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.springbootpro.model.KakaoProfile;
import com.web.springbootpro.model.OAuthToken;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Value("${cos.key}")
    private  String cosKey;
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

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }

    //데이터를 반환해주는 컨트롤러 함수
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        //POST 방식으로 key  = value 데이터 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate();       // REST Api 호출 이후 응답을 받을 때까지 기다리는 템플릿

        HttpHeaders headers = new HttpHeaders();    //HttpHeaders 통해 요청하는 데이터 타입을 지정
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8;");


        // 데이터 요청에 필요한 정보들을 하나의 객체에 담기, 규칙에 따라
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        //위에서 생성한 Headers, params 정보를 하나의 객체로 합쳐 카카오쪽으로 요청
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest
                = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauToken = null;
        try {
            oauToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //객체로 저장한 토큰 정보(access_token)를 통해 사용자 프로필 요청
        RestTemplate rt_ = new RestTemplate();

        HttpHeaders headers_ = new HttpHeaders();
        headers_.add("Authorization", "Bearer " + oauToken.getAccess_token());
        headers_.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers_);

        System.out.println("kakaoProfileRequest.getHeaders() = " + kakaoProfileRequest.getHeaders());

        ResponseEntity<String> response_ = rt_.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );



        //사용자 프로필을 객체로 저장하기
        ObjectMapper objectMapper_ = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper_.readValue(response_.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(cosKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();




        //회원 또는 비회원 확인
        User orginUser = userService.userSeach(kakaoUser.getUsername());

        if (orginUser.getUsername() == null) {
            userService.userJoin(kakaoUser);
        }

        //자동 로그인 처리
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
