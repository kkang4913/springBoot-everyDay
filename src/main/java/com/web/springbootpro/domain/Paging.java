package com.web.springbootpro.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Paging<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;

    public Paging(List<T> data, int currentPage, int totalPages) {
        this.data =  data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
