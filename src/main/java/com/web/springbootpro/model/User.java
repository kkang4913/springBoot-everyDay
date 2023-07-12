package com.web.springbootpro.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Long id;
    private String username;
    private String role;
    private String password;
    private String email;
    private Timestamp createDate;


}
