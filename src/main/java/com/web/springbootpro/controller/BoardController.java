package com.web.springbootpro.controller;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.domain.Paging;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //모든 데이터 출력
    @GetMapping("/dummy/board")
    @ResponseBody
    public Paging<Board> getBoardList(@RequestParam(defaultValue = "1")int page
            , @RequestParam(defaultValue = "5")int size){
        log.info("page={}",page);
        log.info("size={}",size);

        int start = (page -1 ) * size + 1;
        int end   = page * size;

        List<Board> boardList = boardService.getBoardList(start,end);
        int totalBoard = boardService.getTotalBoard();
        log.info("totalBoards={}",totalBoard);

        int totalPages =(int) Math.ceil((double) totalBoard / size);
        log.info("totalPages={}",totalPages);

        return new Paging<>(boardList,page,totalPages);
    }

    /**
     * 게시글 작성 폼
     * @return : 작성 폼 JSP
     */
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    /**
     * 게시글 상세보기
     */

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetail principal){
            System.out.println("게시글 상세보기 함수 실행 = " + id);
            Board board = boardService.findById(id);
            log.info("boardById = {}", board);


            model.addAttribute("board",board);
            model.addAttribute("principal",principal);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model){
        System.out.println("게시물 업데이트 폼 함수 실행 = " + id);
        Board board = boardService.findById(id);
        log.info("board 게시글 정보={}",board);
        model.addAttribute("board",boardService.findById(id));
        return "board/updateForm";

    }
}
