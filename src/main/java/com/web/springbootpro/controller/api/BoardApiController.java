package com.web.springbootpro.controller.api;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.domain.ResponseDto;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.Reply;
import com.web.springbootpro.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.boardWrite(board, principal.getUser());

        log.info("컨트롤러 BOARD={}", board);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id){
        log.info("게시글 삭제 ID = ",id);
        boardService.deleteById(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board){

        log.info("API_id = {},API_board={}",id,board);

        boardService.updateBoard(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.saveComment(boardId,reply,principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
