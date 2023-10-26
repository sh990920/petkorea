package com.project.petkorea.repository;

import com.project.petkorea.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Object> {
    Board findByIdx(Long idx);

    Page<Board> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board SET title=:title, content=:content, writeDate=:writeDate WHERE idx=:idx", nativeQuery = true)
    void updateNickname(@Param("title") String title, @Param("content") String content, @Param("writeDate") String writeDate, @Param("idx") Long idx);
}
