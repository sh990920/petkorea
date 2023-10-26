package com.project.petkorea.controller;

import com.project.petkorea.entity.Board;
import com.project.petkorea.entity.Member;
import com.project.petkorea.service.BoardService;
import com.project.petkorea.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @Autowired
    MemberService memberService;

    @GetMapping("/board/")
    public String boardPage(Model model, @RequestParam(value="page", defaultValue="0") int page){
        Page<Board> boardPage = boardService.findPage(page);
        model.addAttribute("boardList", boardPage);
        return "Board/Board";
    }

    @GetMapping("/board/add/")
    public String boardAddPage(Principal principal, Model model){
        Member member = memberService.findMember(principal.getName());
        model.addAttribute("member", member);
        return "Board/BoardAdd";
    }

    @PostMapping("/board/add/add/")
    public String boardAdd(Board board){
        boardService.boardAdd(board);
        return "redirect:/board/";
    }

    @GetMapping("/board/post/")
    public String boardPostPage(Board board,Principal principal, Model model){
        Board returnBoard = boardService.findBoard(board);
        model.addAttribute("board", returnBoard);
        model.addAttribute("id", principal.getName());
        return "Board/BoardPost";
    }

    @GetMapping("/board/update/")
    public String boardUpdatePage(Board board, Model model){
        Board updateBoard = boardService.findBoard(board);
        model.addAttribute("board", updateBoard);
        return "Board/BoardUpdate";
    }

    @PostMapping("/board/update/update/")
    public String boardUpdate(Board board){
        boardService.boardUpdate(board);
        return "redirect:/board/";
    }

    @PostMapping("/board/delete/")
    public String boardDelete(Board board){
        boardService.boardDelete(board);
        return "redirect:/board/";
    }

}
