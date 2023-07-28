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
public class CheckUserEmailValidator extends AbstractValidator<UserRequestDto> {

    private final UserMapper userMapper;


    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        log.info("이메일 검사={}",dto.getEmail());

        int result = userMapper.existByUserEmail(dto.getEmail());
        boolean isExist = result != 0;
        if (isExist){
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다");
        }
    }
}
