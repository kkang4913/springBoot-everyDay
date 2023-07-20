package com.web.springbootpro.service;

import com.web.springbootpro.mapper.ReplyMapper;
import com.web.springbootpro.model.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    public List<Reply> findById(Long id) {

        List<Reply> reply = replyMapper.getReplyList(id);

        return  reply;
    }
}
