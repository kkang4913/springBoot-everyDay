package com.web.springbootpro.mapper;

import com.web.springbootpro.model.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    List<Reply> getReplyList(Long id);

    void saveComment(Reply requestReply);

    void deleteById(Long replyId);
}
