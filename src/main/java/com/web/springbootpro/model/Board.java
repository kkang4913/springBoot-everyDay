package com.web.springbootpro.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Board {
    private Long id;
    private String title;
    private String content;
    private int count;
    private User userid;
    private Timestamp createDate;
    private String show;

}

