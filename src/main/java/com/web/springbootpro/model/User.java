package com.web.springbootpro.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String role;
    private String password;
    private String email;
    private String nickname;
    private Timestamp createDate;
    private String oauth;


    public User() {

    }
}
