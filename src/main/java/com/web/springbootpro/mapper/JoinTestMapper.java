package com.web.springbootpro.mapper;

import com.web.springbootpro.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinTestMapper {
    void userJoin(User user);

    User findById(Long id);
}
