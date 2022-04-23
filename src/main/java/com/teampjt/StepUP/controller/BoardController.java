package com.teampjt.StepUP.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teampjt.StepUP.board.BoardService;
import com.teampjt.StepUP.command.FreeBoardVO;
import com.teampjt.StepUP.command.UserVO;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	// 리스트 화면 
	@GetMapping("/freeboard_main")
	public String freeboard_main(Model model) {
		ArrayList<FreeBoardVO> list = boardService.fb_getList();
		model.addAttribute("list", list);
		return "board/freeboard_main";
	}

	// 글 등록 페이지
	@GetMapping("/freeboard_write")
	public String freeboard_write() {	
		return "board/freeboard_write";
	}

	// 글 등록 기능 
	@PostMapping("/boardForm")
	public String boardForm(FreeBoardVO freeBoardVO) {
		boardService.fb_regist(freeBoardVO);	
		return "redirect:/board/freeboard_main";
	}
	
	// 글 수정 (불러오기)
	@GetMapping("/freeboard_update")
	public String freeboard_update(FreeBoardVO freeBoardVO, 
			@RequestParam("free_board_no") int free_board_no,
			@RequestParam("user_no")  int user_no,
			HttpSession session, Model model) {	
		
			UserVO userVO = (UserVO)session.getAttribute("userVO");  
			if(userVO.getUser_no() != freeBoardVO.getUser_no()) {
				return "redirect:/board/freeboard_main";
		}
			
		FreeBoardVO fb_VO = boardService.fb_getUpdateList(free_board_no);
		model.addAttribute("fb_VO", fb_VO);
		return "board/freeboard_update";
	}
	
	

	//글 수정 (업데이트)
	@PostMapping("/updateForm")
	public String updateForm(FreeBoardVO freeBoardVO) {
		
		boardService.fb_update(freeBoardVO);
		return "redirect:/board/freeboard_main";
	}
	
	
	//글 삭제
	@GetMapping("/boardDelete")
	public String boardDelete(FreeBoardVO freeBoardVO,
							  @RequestParam("free_board_no") int free_board_no,
							  @RequestParam("user_no")  int user_no,
							  HttpSession session) {	
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO.getUser_no() != freeBoardVO.getUser_no()) {
			return "redirect:/board/freeboard_main";
	}
		
		boardService.fb_delete(freeBoardVO);
		return "redirect:/board/freeboard_main";
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/qnaboard_main")
	public String qnaboard_main() {
		
		return "board/qnaboard_main";
	}
	
	@GetMapping("/qnaboard_write")
	public String qnaboard_write() {
		
		return "board/qnaboard_write";
	}
}
