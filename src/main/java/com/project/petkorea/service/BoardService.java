package com.project.petkorea.service;

import com.project.petkorea.entity.Board;
import com.project.petkorea.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public void boardAdd(Board board){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now  = new Date();
        String nowDate = simpleDateFormat.format(now);
        board.setWriteDate(nowDate);
        boardRepository.save(board);
    }

    public void boardDelete(Board board){
        Board deleteBoard = boardRepository.findByIdx(board.getIdx());
        if(deleteBoard != null){
            boardRepository.delete(deleteBoard);
        }
    }

    public void boardUpdate(Board board){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now  = new Date();
        String nowDate = simpleDateFormat.format(now);
        board.setWriteDate(nowDate);
        boardRepository.updateNickname(board.getTitle(), board.getContent(), board.getWriteDate(), board.getIdx());
    }

    public Board findBoard(Board board){
        Board returnBoard = boardRepository.findByIdx(board.getIdx());
        return returnBoard;
    }

    public List<Board> findAll(){
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Page<Board> findPage(int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }
}
