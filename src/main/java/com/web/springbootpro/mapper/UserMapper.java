package com.web.springbootpro.mapper;

import com.web.springbootpro.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    void userJoin(User user);

    User findById(Long id);

    List<User> findAll();

    List<User> getUserList(@Param("start")int start,@Param("end") int end);

    int getTotalUsers();

    void updateUser(User user);

    void deleteById(Long id);

    void userLogin(User user);

    Optional<User> findByUsername(String username);
}
