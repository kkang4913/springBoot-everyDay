package com.web.springbootpro.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Reply {

    private Long id;
    private String content;
    private Board board;
    private User user;
    private Timestamp createDate;

}
