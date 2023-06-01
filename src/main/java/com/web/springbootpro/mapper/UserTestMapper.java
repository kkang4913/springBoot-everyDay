package com.web.springbootpro.mapper;

import com.web.springbootpro.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UserTestMapper {
    void userJoin(User user);

    User findById(Long id);

    List<User> findAll();

    List<User> getUserList(@Param("start")int start,@Param("end") int end);

    int getTotalUsers();
}
