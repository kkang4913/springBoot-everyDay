package com.web.springbootpro.mapper;

import com.web.springbootpro.model.Reply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
    Reply getReplyList(Long id);
}
