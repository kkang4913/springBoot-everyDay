package com.web.springbootpro.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {
    public boolean supports(Class<?> clazz){
        return true;
    }

    @SuppressWarnings("unchecked")  //컴파일러 경고 무시
    public void validate(Object target, Errors errors){
        try {
            doValidate((T) target,errors);
        }catch (RuntimeException e){
            log.info("중복 검증 에러",e);
            throw e;
        }
    }

    protected  abstract void doValidate(final T dto, final Errors errors);
}
