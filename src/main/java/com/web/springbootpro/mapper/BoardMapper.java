package com.web.springbootpro.mapper;

import com.web.springbootpro.model.Board;
import com.web.springbootpro.model.Reply;
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

    void deleteByReplyId(Long id);

    void updateCount(Long id);

    List<Board> findByTitleContaining(@Param("type") String searchType,@Param("keyword")String searchKeyword,@Param("start")int start, @Param("end") int end);

    int searchTotalBoard(@Param("type")String searchType,@Param("keyword")String searchKeyword);
}
