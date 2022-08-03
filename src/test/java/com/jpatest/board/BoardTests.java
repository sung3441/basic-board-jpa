package com.jpatest.board;

import com.jpatest.board.entity.Board;
import com.jpatest.board.entity.BoardRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// @Transactional
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;

    @Order(1)
    @Test
    void  save() {

        // 1. 게시글 파라미터 생성
        Board params = Board.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .writer("조성일")
                .hits(0)
                .deleteYn('N')
                .build();

        // 2. 게시글 저장
        Board saveBoard = boardRepository.save(params);

        // 3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById(saveBoard.getId()).get();
        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
        assertThat(entity.getWriter()).isEqualTo("조성일");
    }

    @Order(2)
    @Test
    void findAll() {
        List<Board> all = boardRepository.findAll();

        long count = boardRepository.count();

        assertThat(all.size()).isEqualTo(1);
        assertThat(count).isEqualTo(1);
    }

    @Order(3)
    @Test
    void delete() {
        List<Board> all = boardRepository.findAll();
        Board board = all.get(0);
        Long id = board.getId();

        boardRepository.deleteById(id);

        long count = boardRepository.count();
        assertThat(count).isEqualTo(0);
    }
}
