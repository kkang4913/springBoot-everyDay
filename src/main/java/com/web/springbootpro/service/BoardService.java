package com.web.springbootpro.service;

import com.web.springbootpro.mapper.BoardMapper;
import com.web.springbootpro.mapper.ReplyMapper;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.Reply;
import com.web.springbootpro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private ReplyMapper replyMapper;

    public List<Board> getBoardList(int start, int end) {
        return boardMapper.getBoardList(start,end);
    }

    public int getTotalBoard() {
        return boardMapper.getTotalBoard();
    }

    public void boardWrite(Board board, User user) {
        board.setCount(0);
        board.setUserid(user);

        log.info("컨트롤러에서 넘어온 정보={}",board);
        boardMapper.boardSave(board);
    }

    public Board findById(Long id) {
        Board board = boardMapper.findById(id);
        return board;
    }

    public void deleteById(Long id) {
        boardMapper.deleteById(id);
    }

    public void updateBoard(Long id, Board requestBoard){
        log.info("BoardService - update_id = {},update_Board={}",id,requestBoard);

        Board board = boardMapper.findById(id);

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());

        boardMapper.updateBoard(board);
    }

    @Transactional
    public void saveComment(Long boardId, Reply requestReply, User user){
        Board board = boardMapper.findById(boardId);

        board.setUserid(user);

        log.info("BOARD = {}",board);

        log.info("requestReply = {}",requestReply);

        log.info("USER = {}",user);


        requestReply.setBoard(board);
        log.info("저장된 requestReply = {}",requestReply);
        requestReply.setUser(user);


        log.info("저장할 댓글 정보 = {}",requestReply);
        replyMapper.saveComment(requestReply);
    }


}
