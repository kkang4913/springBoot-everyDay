package com.web.springbootpro.service;

import com.web.springbootpro.mapper.BoardMapper;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

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

}
