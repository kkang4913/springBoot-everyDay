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
public class CheckUserNicknameValidator extends AbstractValidator<UserRequestDto> {

    private final UserMapper userMapper;

    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        log.info("닉네임 검사={}",dto.getNickname());
        int result = userMapper.existByUserNickname(dto.getNickname());
        boolean isExist = result != 0;
        if (isExist){
            errors.rejectValue("nickname","닉네임 중복 오류", "이미 사용중인 닉네임 입니다..");
        }
    }
}
