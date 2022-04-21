package com.teampjt.StepUP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teampjt.StepUP.board.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@GetMapping("/freeboard_main")
	public String freeboard_main() {
		
		return "board/freeboard_main";
	}
	
	@GetMapping("/freeboard_write")
	public String freeboard_write() {
		
		return "board/freeboard_write";
	}
	
}
