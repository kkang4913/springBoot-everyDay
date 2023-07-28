package com.web.springbootpro.model;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
public class Reply {

    private Long id;
    private String content;
    private Board board;
    private User user;
    private Timestamp createDate;


    public String getCreateDate() {
        return new SimpleDateFormat("yyyy.MM.dd").format(createDate);
    }
}
