package com.web.springbootpro.controller.api;

import com.web.springbootpro.config.auth.PrincipalDetail;
import com.web.springbootpro.domain.ResponseDto;
import com.web.springbootpro.model.Board;
import com.web.springbootpro.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

}
