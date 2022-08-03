package com.jpatest.board.model;

import com.jpatest.board.dto.BoardResponseDto;
import com.jpatest.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 수 조회
     */
    int count(final CommonParams params);

    /**
     * 게시글 리스트 조회
     */
    List<BoardResponseDto> findAll(final CommonParams params);
}
