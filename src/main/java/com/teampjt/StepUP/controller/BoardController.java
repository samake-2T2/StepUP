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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teampjt.StepUP.board.BoardService;
import com.teampjt.StepUP.command.FreeBoardVO;
import com.teampjt.StepUP.command.MainCommentsVO;
import com.teampjt.StepUP.command.QnaBoardVO;
import com.teampjt.StepUP.command.QnaCommentsVO;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.util.PageVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	//*****자유게시판*****
	
	// 리스트 화면 
	@GetMapping("/freeboard_main")
	public String freeboard_main(Model model, Criteria cri
			) {
		System.out.println("search_value = "+cri);
		//페이징 처리
		
		//게시물 리스트
		ArrayList<FreeBoardVO> list = boardService.fb_getList(cri);
		int total = boardService.fb_getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		for(int listCnt = 0;listCnt <list.size();listCnt++) {	
			//댓글 VO 선언
			MainCommentsVO mainCommentsVO = new MainCommentsVO();
			// 댓글 VO에 해당 게시물의 고유번호를 SET
			mainCommentsVO.setFree_board_no(   list.get(listCnt).getFree_board_no()    );
			// 게시물 고유번호로 댓글 조회
			ArrayList<MainCommentsVO> c_list = boardService.mc_getList( mainCommentsVO );
			// 댓글 리스트를 게시물 VO에 SET
			list.get(listCnt).setMain_comments_list(c_list);
		}
		System.out.println("pageVO = "+pageVO);
		// 데이터 저장
		model.addAttribute("list", list);
		// 페이지네이션 저장 
		model.addAttribute("pageVO", pageVO);
		return "board/freeboard_main";
	}
	
	// 글 등록 페이지
	@GetMapping("/freeboard_write")
	public String freeboard_write() {	
		
		return "board/freeboard_write";
	}
	
	// 글 등록 기능 
	@PostMapping("/boardForm")
	public String boardForm(FreeBoardVO freeBoardVO, RedirectAttributes RA,
						    HttpSession session) {
		//유저확인 (로그아웃 상태에서 글등록 안 들어가는지 확인 필요!!!!!!!!!!!!!!!!!!!!!!!!!!1)
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != freeBoardVO.getUser_no()) {
			return "redirect:/board/freeboard_main";
		}
		// 실행
		int result = boardService.fb_regist(freeBoardVO);	
		if(result == 1) { 
			RA.addFlashAttribute("msg", "등록되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/freeboard_main";
	}
	
	// 글 수정 (불러오기)
	@GetMapping("/freeboard_update")
	public String freeboard_update(FreeBoardVO freeBoardVO, 
			@RequestParam("free_board_no") int free_board_no,
			@RequestParam("user_no")  int user_no,
			HttpSession session, Model model) {	
		// 실행
		FreeBoardVO fb_VO = boardService.fb_getUpdateList(free_board_no);
		model.addAttribute("fb_VO", fb_VO);
		return "board/freeboard_update";
	}
	
	//글 수정 (업데이트)
	@PostMapping("/updateForm")
	public String updateForm(FreeBoardVO freeBoardVO, 
							RedirectAttributes RA) {
		// 실행
		int result = boardService.fb_update(freeBoardVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "수정되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/freeboard_main";
	}
	
	//글 삭제
	@GetMapping("/boardDelete")  
	public String boardDelete(FreeBoardVO freeBoardVO,
							  @RequestParam("free_board_no") int free_board_no,
							  @RequestParam("user_no")  int user_no,
							  HttpSession session, RedirectAttributes RA) {	
		// 실행
		int result = boardService.fb_delete(freeBoardVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "삭제되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/freeboard_main";
	}
	
	//댓글 등록
	@PostMapping("/mainCommentForm")
	public String mainCommentForm(MainCommentsVO mainCommentsVO,
						  		    HttpSession session, RedirectAttributes RA) {
		//유저확인
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != mainCommentsVO.getUser_no()) {
			System.out.println("세션없음....");
			return "redirect:/board/freeboard_main";
		}
		//실행
		int result = boardService.mc_regist(mainCommentsVO);	
		if(result == 1) { 
			RA.addFlashAttribute("msg", "댓글이 등록되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/freeboard_main";
	}
	
	//댓글 삭제
	@GetMapping("/deleteComment")
	public String deleteComment(MainCommentsVO mainCommentsVO,
					  		    HttpSession session,
					  		    RedirectAttributes RA) {
		//유저확인
//		UserVO userVO = (UserVO)session.getAttribute("userVO");  
//		if(userVO == null || userVO.getUser_no() != mainCommentsVO.getUser_no()) {
//			System.out.println("세션없음....");
//			return "redirect:/board/freeboard_main";
//		}
		//실행
		int result = boardService.mc_delete(mainCommentsVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "삭제되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
			}
		System.out.println(result);
		return "redirect:/board/freeboard_main";
	}
	
	// 댓글 수정
	@PostMapping("/updateComment")
	public String freeboard_update(MainCommentsVO mainCommentsVO,
		    @RequestParam("writer") int writer,
  		    HttpSession session, Model model,
  		    RedirectAttributes RA) {	
		//유저확인
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != mainCommentsVO.getUser_no()) {
			System.out.println("세션없음....");
			return "redirect:/board/freeboard_main";
		}
		int result =boardService.mc_UpdateContents(mainCommentsVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "수정되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
			}
		System.out.println(result);
		return "redirect:/board/freeboard_main";
	}

	//*****문의게시판*****
	
	// 리스트 화면 
	@GetMapping("/qnaboard_main")
	public String qnaboard_main(Model model, Criteria cri
			) {
		System.out.println("search_value = "+cri);
		//페이징 처리
		
		//게시물 리스트
		ArrayList<QnaBoardVO> list = boardService.qna_getList(cri);
		int total = boardService.qna_getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		for(int listCnt = 0; listCnt < list.size(); listCnt++) {	
			//댓글 VO 선언
			QnaCommentsVO qnaCommentsVO = new QnaCommentsVO();
			// 댓글 VO에 해당 게시물의 고유번호를 SET
			qnaCommentsVO.setQna_board_no(list.get(listCnt).getQna_board_no());
			// 게시물 고유번호로 댓글 조회
			ArrayList<QnaCommentsVO> c_list = boardService.qc_getList(qnaCommentsVO);
			// 댓글 리스트를 게시물 VO에 SET
			list.get(listCnt).setQna_comments_list(c_list);
			
		}
		System.out.println("pageVO = "+pageVO);
		// 데이터 저장
		model.addAttribute("list", list);
		// 페이지네이션 저장 
		model.addAttribute("pageVO", pageVO);
		return "board/qnaboard_main";
	}
	
	// 글 등록 페이지
	@GetMapping("/qnaboard_write")
	public String qnaboard_write() {	
		
		return "board/qnaboard_write";
	}
	
	// 글 등록 기능 
	@PostMapping("/qnaboardForm")
	public String qnaForm(QnaBoardVO qnaBoardVO, RedirectAttributes RA,
						    HttpSession session) {
		//유저확인 (로그아웃 상태에서 글등록 안 들어가는지 확인 필요!!!!!!!!!!!!!!!!!!!!!!!!!!1)
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != qnaBoardVO.getUser_no()) {
			return "redirect:/board/freeboard_main";
		}
		// 실행
		int result = boardService.qna_regist(qnaBoardVO);	
		if(result == 1) { 
			RA.addFlashAttribute("msg", "등록되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/qnaboard_main";
	}
	
	// 글 수정 (불러오기)
	@GetMapping("/qnaboard_update")
	public String qnaboard_update(QnaBoardVO qnaBoardVO, 
			@RequestParam("qna_board_no") int qna_board_no,
			@RequestParam("user_no")  int user_no,
			HttpSession session, Model model) {	
		// 실행
		QnaBoardVO qna_VO = boardService.qna_getUpdateList(qna_board_no);
		model.addAttribute("qna_VO", qna_VO);
		return "board/qnaboard_update";
	}
	
	//글 수정 (업데이트)
	@PostMapping("/qnaupdateForm")
	public String qnaupdateForm(QnaBoardVO qnaBoardVO, 
							RedirectAttributes RA) {
		// 실행
		int result = boardService.qna_update(qnaBoardVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "수정되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/qnaboard_main";
	}
	
	//글 삭제
	@GetMapping("/qnaboardDelete")  
	public String qnaboardDelete(QnaBoardVO qnaBoardVO,
							  @RequestParam("qna_board_no") int qna_board_no,
							  @RequestParam("user_no")  int user_no,
							  HttpSession session, RedirectAttributes RA) {	
		// 실행
		int result = boardService.qna_delete(qnaBoardVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "삭제되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/qnaboard_main";
	}
	
	//댓글 등록
	@PostMapping("/qnaCommentForm")
	public String qnaCommentForm(QnaCommentsVO qnaCommentsVO,
						  		    HttpSession session, RedirectAttributes RA) {
		//유저확인
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != qnaCommentsVO.getUser_no()) {
			System.out.println("세션없음....");
			return "redirect:/board/qnaboard_main";
		}
		//실행
		int result = boardService.qc_regist(qnaCommentsVO);	
		if(result == 1) { 
			RA.addFlashAttribute("msg", "댓글이 등록되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
			}
		return "redirect:/board/qnaboard_main";
	}
	
	//댓글 삭제
	@GetMapping("/qnadeleteComment")
	public String qnadeleteComment(QnaCommentsVO qnaCommentsVO,
					  		    HttpSession session,
					  		    RedirectAttributes RA) {
		//실행
		int result = boardService.qc_delete(qnaCommentsVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "삭제되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
			}
		System.out.println(result);
		return "redirect:/board/qnaboard_main";
	}
	
	// 댓글 수정
	@PostMapping("/qnaupdateComment")
	public String freeboard_update(QnaCommentsVO qnaCommentsVO,
		    @RequestParam("writer") int writer,
  		    HttpSession session, Model model,
  		    RedirectAttributes RA) {	
		//유저확인
		UserVO userVO = (UserVO)session.getAttribute("userVO");  
		if(userVO == null || userVO.getUser_no() != qnaCommentsVO.getUser_no()) {
			System.out.println("세션없음....");
			return "redirect:/board/qnaboard_main";
		}
		int result =boardService.qc_UpdateContents(qnaCommentsVO);
		if(result == 1) { 
			RA.addFlashAttribute("msg", "수정되었습니다. ");
			}else { 
			RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
			}
		System.out.println(result);
		return "redirect:/board/qnaboard_main";
	}
}
