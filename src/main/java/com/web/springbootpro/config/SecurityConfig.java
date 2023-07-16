package com.web.springbootpro.config;

import com.web.springbootpro.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 설정 파일을 빈(Bean) 등록 : 스프링 컨테이너에서 객체 관리 가능
@EnableWebSecurity // 시큐리티 필터(Filter) 설정 : 스프링 시큐리티의 설정을 해당 파일에서 관리
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증을 미리 체크 하는 기능
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
    }


    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        /**
         * configure 함수 : "/auth/.. 로부터 요청되는 경로는 모두 승인 "
         *                  나머지 경로들에 대해서는 인증이 필요하다는 의미
         *                  인증이 되지 않은 사용자가 해당 경로에 접근하려 할 경우 로그인을 위한 페이지를 출력
         *
         */
        http
            .csrf().disable()   // csrf 비활성화 : 테스트를 위해 사용
                                // 이유 : 자바 스크립트 방식 -> 시큐리티에서 공격으로 간주, 막아버림
                                //        테스트를 위해 기능 비활성화
            .authorizeRequests()
                .antMatchers("/**","/auth/**","/webapp/resources/**","/static/js/**","/css/**","image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")  //스프링 시큐리티가 해당 주소로 오는 로그인 요청을 가로채서 대신 로그인을 수행
                .defaultSuccessUrl("/");                // 로그인 성공 시 이동하게 될 경로
    }
    @Bean // 어노테이션을 메서드에 명시 : 스프링 빈 등록, 해당 객체를 원할 때 사용 가능
    public BCryptPasswordEncoder encoderPWD(){
        /**
         * 해당 객체는 암호화하기 위한 메소드를 가지고 있다.
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
