package com.web.springbootpro.mapper;

import com.web.springbootpro.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList(@Param("start")int start, @Param("end") int end);

    int getTotalBoard();

    void boardSave(Board board);

    Board findById(Long id);

    void deleteById(Long id);

    void updateBoard(Board board);
}
