package com.web.springbootpro.domain;

import com.web.springbootpro.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Page<U> {
    private List<U> data;
    private int currentPage;
    private int totalPages;

    public Page(List<U> data, int currentPage, int totalPages) {
        this.data =  data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
