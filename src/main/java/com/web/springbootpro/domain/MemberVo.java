package com.web.springbootpro.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberVo {
    private Long id;
    private String username;
    private String password;
    private String email;

}
