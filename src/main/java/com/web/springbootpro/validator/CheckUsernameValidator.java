package com.web.springbootpro.validator;

import com.web.springbootpro.dto.UserRequestDto;
import com.web.springbootpro.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@RequiredArgsConstructor
@Component
@Slf4j
public class CheckUsernameValidator extends AbstractValidator<UserRequestDto> {

    private final UserMapper userMapper;


    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        log.info("유저이름 검사={}",dto.getUsername());

        int result = userMapper.existByUsername(dto.getUsername());
        boolean isExist = result != 0;
        if (isExist){
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다");
        }
    }
}
