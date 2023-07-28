package com.web.springbootpro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@EntityScan
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private int count;
    private User userid;
    private Timestamp createDate;
    private String show;


    public String getCreateDate() {
        return new SimpleDateFormat("yyyy.MM.dd").format(createDate);
    }
}

