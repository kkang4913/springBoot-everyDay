package com.web.springbootpro.controller;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.domain.Paging;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.Reply;
import com.web.springbootpro.model.User;
import com.web.springbootpro.service.BoardService;
import com.web.springbootpro.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;


    //모든 데이터 출력
    @GetMapping("/dummy/board")
    @ResponseBody
    public Paging<Board> getBoardList(@RequestParam(defaultValue = "1")int page
            , @RequestParam(defaultValue = "5")int size
            , @RequestParam(defaultValue = "") String searchKeyword
            , @RequestParam(defaultValue = "") String searchType){

        int start = (page -1 ) * size + 1;
        int end   = page * size;

        List<Board> boardList = boardService.getBoardList(start,end);
        int totalBoard = boardService.getTotalBoard();

        int totalPages =(int) Math.ceil((double) totalBoard / size);


        if (searchKeyword.equals("")){
            return new Paging<>(boardList,page,totalPages);
        }else {
            List<Board> searchBoardList = boardService.findByTitleContaining(searchType,searchKeyword,start,end);
            log.info("검색 함수 실행 ={} , 타입={}, 키워드={}",searchBoardList,searchType,searchKeyword);
            int searchBoard = boardService.searchTotalBoard(searchType,searchKeyword);
            int searchTotalPages =(int) Math.ceil((double) searchBoard / size);

            return new Paging<>(searchBoardList,page,searchTotalPages);
        }
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
    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetail principal,
                           HttpServletRequest request, HttpServletResponse response){

            Long userId = principal != null ? principal.getUser().getId() : null;

            Board board = boardService.detail(id,request,response,userId);
            List<Reply> reply =replyService.findById(id);

            model.addAttribute("board",board);
            model.addAttribute("reply",reply);
            model.addAttribute("principal",principal);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal PrincipalDetail principal){
        Board board = boardService.detail(id,request,response,principal.getUser().getId());
        model.addAttribute("board",board);
        return "board/updateForm";

    }
}
