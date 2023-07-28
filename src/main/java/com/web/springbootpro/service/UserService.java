package com.web.springbootpro.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.springbootpro.dto.UserRequestDto;
import com.web.springbootpro.model.KakaoProfile;
import com.web.springbootpro.model.OAuthToken;
import com.web.springbootpro.model.User;
import com.web.springbootpro.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Value("${cos.key}")
    private  String cosKey;



    @Transactional(readOnly = true)
    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional(readOnly = true)
    public void kakaoLogin(String code){
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
                .nickname(kakaoProfile.getProperties().getNickname())
                .oauth("kakao")
                .build();




        //회원 또는 비회원 확인
        User orginUser = userSeach(kakaoUser.getUsername());

        if (orginUser.getUsername() == null) {
            userJoin(kakaoUser);
        }

        //자동 로그인 처리
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }



    /**
     * 회원가입
     * @param user
     */
    @Transactional
    public void userJoin(User user) {
        String rawPassword = user.getPassword();             //원문 패스워드
        String encPassword = encoder.encode(rawPassword);    //해쉬 패스워드

        user.setPassword(encPassword);
        user.setRole("USER");
        log.info("가입하는 유저 플랫폼 = {}", user.getOauth());
        log.info("일반 회원가입 유저 정보 ={}" ,user);
        mapper.userJoin(user);
    }

    @Transactional
    public void userJoin(UserRequestDto userDto){
        User user = User.builder()
                .username(userDto.getUsername())
                .password(encoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .role("USER")
                .build();

        mapper.userJoin(user);
    }

    public User findById(Long id) {
       User user = mapper.findById(id);
        return user;
    }

    public List<User> findAll() {
        return mapper.findAll();
    }

    public List<User> getUserList(int start,int end) {
        return mapper.getUserList(start,end);
    }

    public int getTotalUsers() {
        return mapper.getTotalUsers();
    }


    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    public void deleteById(Long id) {
        mapper.deleteById(id);
    }

    public User userLogin(User user) {
        System.out.println("userService 실행 = " + user);
        mapper.userLogin(user);
        return user;
    }

    @Transactional
    public void userUpdate(User user) {
        System.out.println("userService[회원정보수정] = " + user);
        User persistence = mapper.findById(user.getId());
        log.info("persistence={}",persistence);
        if (persistence ==null) {
            throw  new IllegalArgumentException("회원찾기 실패");
        }
        String rawPassword = user.getPassword(); //원문 패스워드
        String encPassword = encoder.encode(rawPassword); //해쉬 패스워드

        persistence.setPassword(encPassword);
        persistence.setNickname(user.getNickname());

        log.info("업데이트 회원 정보={}",persistence);

        mapper.userUpdate(persistence);
    }
    @Transactional
    public User userSeach(String username) {
        User user = mapper.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }

    @Transactional
    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String,String> validatorResult = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }
}
