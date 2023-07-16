package com.web.springbootpro.config.auth;

import com.web.springbootpro.mapper.UserMapper;
import com.web.springbootpro.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("가로챈 유저 네임={}",username);
        User principal = userMapper.findByUsername(username).orElseThrow(()-> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
        });
        log.info("로그인한 유저 정보 테스트 = {} " ,principal);

        return new PrincipalDetail(principal);  // 스프링 시큐리티 세션에 유저 정보가 저장됨
    }
}
