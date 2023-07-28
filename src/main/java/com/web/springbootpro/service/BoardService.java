package com.web.springbootpro.service;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.mapper.BoardMapper;
import com.web.springbootpro.mapper.ReplyMapper;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.Reply;
import com.web.springbootpro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public Board detail(Long id, HttpServletRequest request, HttpServletResponse response, Long principal_id) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null )
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("boardView"))
                    oldCookie = cookie;

        if (oldCookie != null){
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")){
                boardMapper.updateCount(id);
                oldCookie.setValue(oldCookie.getValue()+ "[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60*60*24);
                response.addCookie(oldCookie);
            }
        }
        else {
            boardMapper.updateCount(id);
            Cookie newCookie = new Cookie("boardView","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60*60*24);
            response.addCookie(newCookie);
        }
        Optional<Board> boardOptional = Optional.ofNullable(boardMapper.findById(id));
        Board board = boardOptional.orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));

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


    public void deleteByReplyId(Long id) {
        boardMapper.deleteByReplyId(id);
    }

    public List<Board> findByTitleContaining(String searchType,String searchKeyword, int start, int end) {
        return boardMapper.findByTitleContaining(searchType,searchKeyword,start,end);
    }

    public int searchTotalBoard(String searchType,String searchKeyword) {
       return boardMapper.searchTotalBoard(searchType,searchKeyword);
    }
}
