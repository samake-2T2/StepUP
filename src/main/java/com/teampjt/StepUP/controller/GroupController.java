package com.teampjt.StepUP.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teampjt.StepUP.command.GroupBoardVO;
import com.teampjt.StepUP.command.GroupCommentsVO;
import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupMemberVO;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.LikeCountVO;
import com.teampjt.StepUP.command.RequestVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.command.ToDoListVO;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.group.GroupService;
import com.teampjt.StepUP.user.UserService;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.util.PageVO;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	public UserService userService;

	@Autowired
	private GroupService groupService;

	@GetMapping("/groupApplication")
	public String groupApplication(@RequestParam("group_no") int group_no,
								   Model model) {
		
		
		
		model.addAttribute("group_no", group_no);
		return "group/groupApplication";
	}

	@GetMapping("/groupMain")
	public String groupMain(@RequestParam("group_no") int group_no,
							Criteria cri,
							Model model) {
		
		StudyGroupVO SGvo = groupService.getStudyGroupDetail(group_no);
		ArrayList<GroupNoticeVO> list1 = groupService.getNoticeView(group_no);
		ArrayList<GroupBoardVO> list2 = groupService.gb_getListview();
		LikeCountVO LKvo = LikeCountVO.builder().group_no(group_no).build();
		int like = groupService.like_count(LKvo);
		
		
		model.addAttribute("SGvo", SGvo);
		model.addAttribute("GNlist", list1);
		model.addAttribute("GBlist", list2);
		model.addAttribute("like", like);
		
		return "group/groupMain";
	}

	@GetMapping("/groupRegist")
	public String groupRegist() {
		return "group/groupRegist";
	}

	// 내가 가입한 그룹리스트
	@GetMapping("/groupList")
	public String groupList(@RequestParam("user_no") int user_no,
							Model model) {
		
		ArrayList<Integer> list1 = groupService.getMyGroupNoList1(user_no);
		ArrayList<Integer> list2 = groupService.getMyGroupNoList2(user_no);
		
		ArrayList<StudyGroupVO> list = new ArrayList<>();
		
		if(list1 != null) {
			for(int i : list1) {
				list.add(groupService.getStudyGroupDetail(i)); 
			}				
		}
		
		if(list2 != null) {				
			for(int j : list2) {
				list.add(groupService.getStudyGroupDetail(j));
			}
		}
		
		model.addAttribute("list", list);
		
		return "group/groupList";
		
	}

	//그룹 신청인 목록 조회(그룹장이 확인)
	@GetMapping("/groupRegList")
	public String groupRegList(Model model,
			                   Criteria cri,
			                   @RequestParam("group_no") int group_no) {
									

		//리스트에 정보 담기
		ArrayList<RequestVO> list = userService.getApplyList(group_no);

		//데이터 저장
		model.addAttribute("applylist", list);

		//페이지네이션 저장
		return "group/groupRegList";
	}
	
	//신청 수락
	@PostMapping("/request_ok")
	@ResponseBody
	public Map<String, Object> request_ok(GroupMemberVO vo) {
		
		System.out.println(vo.toString()); 
		
		Map<String, Object> map = new HashMap<>();
		
		RequestVO RQvo = RequestVO.builder()
				 		.user_no(vo.getUser_no())
				 		.group_no(vo.getGroup_no())
				 		.build();
		
		// 이미 해당 그룹에 가입중인 멤버인지 확인
		int result = groupService.requestChk(vo);
		
		if(result >= 1) {
			ArrayList<RequestVO> list = userService.getApplyList(vo.getGroup_no());
			map.put("list", list);
			map.put("result", result);
			
			return map;
		} else {
			groupService.requestOk(vo);
			groupService.req_delete(RQvo);
			ArrayList<RequestVO> list = userService.getApplyList(vo.getGroup_no());
			System.out.println(list.toString());
			map.put("list", list);
			map.put("result", result);
			
			return map;
		}
	}
	//신청 거절
	@PostMapping("/request_no")
	@ResponseBody
	public Map<String, Object> request_no(RequestVO vo) {
		
		System.out.println(vo.toString());  
		
		int result = groupService.req_delete(vo);
		System.out.println(result);
		
		ArrayList<RequestVO> list = userService.getApplyList(vo.getGroup_no());
		System.out.println(list.toString());

		Map<String, Object> map = new HashMap<>();
		
		if(result == 1) {
			map.put("result", result);
			map.put("list", list);
			map.put("msg", "거절되었습니다.");
			
			return map;
		} else {
			map.put("result", result);
			map.put("list", list);
			map.put("msg", "거절에 실패했습니다. 관리자에게 문의하세요.");
			
			return map;
		}
		
	}

	//공지목록페이지
	@GetMapping("/groupNotice")
	public String groupNotice( Model model, 
							   Criteria cri, 
							   @RequestParam("group_no") int group_no,
							   @RequestParam("group_leader_name") String group_leader_name) {
		
		cri.setGroup_no(group_no);
		
		ArrayList<GroupNoticeVO> noticeList = groupService.getNoticeList(cri);
		int total = groupService.GNgetTotal();
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("group_no", group_no);
		model.addAttribute("group_leader_name", group_leader_name);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pageVO", pageVO);
		
		return "group/groupNotice";
	}

	//공지상세보기
	@GetMapping("/groupNoticeDetail")
	public String groupNoticeDetail(@RequestParam("groupnotice_no") int groupnotice_no,
			Model model, @RequestParam("group_no") int group_no) {

		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		GroupNoticeVO gnVO = groupService.getNoticeDetail(groupnotice_no);
		
		model.addAttribute("groupVO", groupVO);
		model.addAttribute("gnVO", gnVO);
		model.addAttribute("move", groupService.movePage(groupnotice_no));

		groupService.updatecount(groupnotice_no);

		return "group/groupNoticeDetail";
	}

	//공지등록페이지
	@GetMapping("/groupNoticeReg")
	public String groupNoticeReg(@RequestParam("group_no") int group_no, Model model) {
		
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		model.addAttribute("groupVO", groupVO);
		
		return "group/groupNoticeReg";
	}

	//그룹공지등록 폼
	@PostMapping("/groupNoticeForm")
	public String groupNoticeForm(GroupNoticeVO gnVO, Model model,
			RedirectAttributes RA, @RequestParam("group_no") int group_no) {

		
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		int result = groupService.noticeRegist(gnVO);
		
		
		RA.addAttribute("group_no", group_no);
		RA.addAttribute("group_leader_name", groupVO.getGroup_leader_name());
		
		model.addAttribute("groupVO", groupVO);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "공지가 등록되었습니다"); 
		}else {
			RA.addFlashAttribute("msg", "공지등록에 실패하였습니다");
		}



		return "redirect:/group/groupNotice";
	}

	//그룹공지수정페이지
	@GetMapping("/groupNoticeModify")
	public String groupNoticeModify(@RequestParam("groupnotice_no") int groupnotice_no,
			Model model,@RequestParam("group_no") int group_no) {
		
		
		
		GroupNoticeVO gnVO = groupService.getNoticeModify(groupnotice_no);
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);	
		
		model.addAttribute("groupVO", groupVO);
		model.addAttribute("gnVO", gnVO);
		
		
		return "group/groupNoticeModify";
	}

	//공지수정 폼
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(GroupNoticeVO gnVO,
			RedirectAttributes RA, Errors errors, Model model, @RequestParam("group_no") int group_no) {
		
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				System.out.println(err.getField());
				System.out.println(err.getDefaultMessage());
				
				if(err.isBindingFailure()) {//자바측 에러인 경우
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요"); //직접 에러메세지 생성
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage()); //유효성 검사 실패 메시지
				}
			}
		}
		
		int result = groupService.noticeUpdate(gnVO);

		RA.addAttribute("group_no", group_no);
		RA.addAttribute("group_leader_name", groupVO.getGroup_leader_name());
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "공지가 수정되었습니다");
			System.out.println(result);
			
		}else {
			RA.addFlashAttribute("msg", "공지가 수정되지않았습니다");
		}

		return "redirect:/group/groupNotice";
	}

	//공지 삭제폼
	@PostMapping("/noticeDelete")
	public String noticeDelete(@RequestParam("groupnotice_no") int groupnotice_no,
			RedirectAttributes RA, @RequestParam("group_no") int group_no) {
		
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		
		RA.addAttribute("group_no", group_no);
		RA.addAttribute("group_leader_name", groupVO.getGroup_leader_name());
		
		
		
		groupService.noticeDelete(groupnotice_no);

		
		
		return "redirect:/group/groupNotice";
	}
	
	//그룹 댓글 목록
	@GetMapping("/groupCommentList")
	public String groupCommentList(Model model) {
		
		ArrayList<GroupDetailCommentVO> list = groupService.getGroupCommentList();
		System.out.println(list.toString());
		
		model.addAttribute("list", list);
		
		return "group/groupDetail";
	
	}
	// 그룹상세 페이지
	@GetMapping("/groupDetail")
	public String groupDetail(Model model,
			                  @RequestParam("group_no") int group_no) {
		
		StudyGroupVO groupVO = groupService.getStudyGroupDetail(group_no);
		int mtotal = groupService.getMemberTotal(group_no);
		
		ArrayList<GroupDetailCommentVO> list = groupService.getGroupCommentList();
		System.out.println(list.toString());
		
		
		model.addAttribute("list", list);
		model.addAttribute("group_no", group_no);
		model.addAttribute("groupVO", groupVO);
		model.addAttribute("mtotal", mtotal+1);
		
		return "group/groupDetail";
	}

	
	//댓글 등록폼
	@PostMapping("/groupCommentForm")
	public String groupCommentForm(GroupDetailCommentVO gdcVO, Model model,
								  RedirectAttributes RA) {
	
		
		int result = groupService.commentRegist(gdcVO);

		
		if(result == 1) {
			RA.addFlashAttribute("msg", "댓글이 등록되었습니다"); 
		}else {
			RA.addFlashAttribute("msg", "댓글등록이 실패하였습니다");
		}
	
		return "redirect:/group/groupDetail?group_no="+ gdcVO.getGroup_no();
	}


	//댓글 수정폼
	@PostMapping("/groupCommentUpdate")
	public String groupCommentUpdate(GroupDetailCommentVO gdcVO,
									 RedirectAttributes RA ) {
		
		
		int result = groupService.commentUpdate(gdcVO);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "댓글이 수정되었습니다");
		}else {
			RA.addFlashAttribute("msg", "댓글이 수정되지않았습니다");
		}

		return "redirect:/group/groupDetail";
	}
	
	//댓글 삭제폼
	@GetMapping("/groupCommentDelete")
	public String groupCommentDelete(@RequestParam("comment_no") int comment_no,
									 RedirectAttributes RA, @RequestParam("group_no") int group_no) {
		
		
		int result = groupService.commentDelete(comment_no);
		
		
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "댓글이 삭제되었습니다");
		}else {
			RA.addFlashAttribute("msg", "댓글이 삭제되지않았습니다");
		}

		return "redirect:/group/groupDetail";

		
	}
	

	// 그룹 이름 중복체크
	@PostMapping("/nameChk")
	@ResponseBody
	public int nameChk(@RequestParam("group_name") String group_name) {
	
		System.out.println(group_name);
		
		if(group_name == null || group_name.equals("")) {
			return 1;
		} else {			
			return groupService.nameChk(group_name);
		}
		
	}

	// 그룹 생성폼
	@PostMapping("/group_reg_form")
	public String group_reg_form(StudyGroupVO vo,
			RedirectAttributes RA,
			@RequestParam("file") MultipartFile f) {
		
		int result = groupService.nameChk(vo.getGroup_name());
		
		if(result == 0) {
			// 1. 업로드된 확장자가 이미지만 가능하도록 처리
			if(f.getContentType().contains("image") == false ) { // 이미지가 아닌경우
				RA.addFlashAttribute("msg", "jpg, png, jpeg등의 이미지형식만 등록가능합니다.");
				return "redirect:/group/groupList";
			}


			groupService.groupRegist(vo, f);

			return "redirect:/main";
		} else {
			return "group/groupRegist";
		}
		
	}
	
	//그룹 신청폼
	@PostMapping("/groupApplicationForm")
	public String groupApplicationForm(@Valid RequestVO reqVO,
									   Errors errors, Model model,
									   RedirectAttributes RA) {
		
		System.out.println(reqVO.toString());
		int result = groupService.getReqChk(reqVO);
		System.out.println(result);
		
		if(result > 0) { // 가입한 이력이 있는경우			
			RA.addFlashAttribute("msg", "이미 가입신청한 그룹입니다");
			
			return "redirect:/main";
		} else { // 이미 가입한 이력이 없는 경우
			groupService.groupApplicationReg(reqVO);
			
			model.addAttribute("vo", reqVO);
			
			if(errors.hasErrors()) {
				List<FieldError> list = errors.getFieldErrors();
				
				for(FieldError err : list) {
					System.out.println(err.getField());
					System.out.println(err.getDefaultMessage());
				}
				
			}
			RA.addFlashAttribute("msg", "가입요청에 성공하였습니다");
			return "redirect:/main";
		}
	}
	
	// 그룹멤버 탈퇴
	@GetMapping("/groupSecession")
	public String groupSecession(@RequestParam("group_no") int group_no,
								 @RequestParam("user_no") int user_no,
								 RedirectAttributes RA) {
		
		//System.out.println(group_no);
		//System.out.println(user_no);
		
		GroupMemberVO vo = GroupMemberVO.builder()
										.group_no(group_no)
										.user_no(user_no)
										.build();
		
		int result = groupService.groupSecession(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "성공적으로 탈퇴되었습니다.");
			
			return "redirect:/main";
		} else {
			RA.addFlashAttribute("msg", "탈퇴에 실패하였습니다.");
			
			return "redirect:/main";
		}
		
		
		
		
	}
	
	// 그룹 투두리스트 비동기 등록 폼
	@PostMapping("/todoReg")
	@ResponseBody
	public ArrayList<ToDoListVO> todoReg(ToDoListVO vo) {
		
//		System.out.println(vo.toString());
		
		groupService.toDoListReg(vo);
		
		ArrayList<ToDoListVO> list = groupService.getToDoList(vo);
		
//		for(ToDoListVO tdvo : list) {
//			System.out.println(tdvo.toString());
//		}
		
		return list;
	}
	
	// 일정 조회 메서드
	@GetMapping("/getToDo")
	@ResponseBody
	public ArrayList<ToDoListVO> getToDo(@RequestParam("date") String date, @RequestParam("group_no") int group_no) {
		
		ToDoListVO vo = new ToDoListVO();
		ToDoListVO TDvo = vo.builder()
							.regdate(date)
							.group_no(group_no)
							.build();
		
		//System.out.println(TDvo.toString());
		ArrayList<ToDoListVO> list = groupService.getToDoList(TDvo);
		
		return list;
		
	}
	
	// 일정 삭제 메서드
	@PostMapping("/todoDelete")
	@ResponseBody
	public int todoDelete(@RequestParam("todo_no") int todo_no) {
		int result = groupService.toDoDelete(todo_no);
		
		return result;
	}
	
	// 일정 수정 메서드
	@PostMapping("/todoUpdate")
	@ResponseBody
	public ArrayList<ToDoListVO> todoUpdate(ToDoListVO vo) {
		
		int result = groupService.todoUpdate(vo);
		
		
		
		ArrayList<ToDoListVO> list = groupService.getToDoList(vo);
		
		
		return list;
		
		
	}
	
	
	// 좋아요 버튼
	@PostMapping("like_count")
	@ResponseBody
	public int like_count(LikeCountVO vo) {
		
		// 해당유저의 좋아요 여부 확인 메서드
		int chk = groupService.like_count_chk(vo);
		
		int result = 0;
		
		// 종아요 여부에 따른 처리(0이면 좋아요 등록 및 카운트, 삭제후 카운트)
		if(chk == 0) {
			// 좋아요 등록 메서드
			groupService.like_count_reg(vo);
			
			// 좋아요 갯수 카운트 메서드
			result = groupService.like_count(vo);
			
			return result;
		}else {
			// 좋아요 취소 메서드
			groupService.like_delete(vo);
			
			// 좋아요 갯수 카운트 메서드
			result = groupService.like_count(vo);
			
			return result;
		}
		
	}
	
	/////////// 그룹 전용 게시판 //////////////////////////////
	
		// 리스트 화면 
		@GetMapping("/groupBoard")
		public String freeboard_main(Model model, Criteria cri) {
			System.out.println("search_value = "+cri);
			//페이징 처리
			
			//게시물 리스트
			//ArrayList<FreeBoardVO> list = boardService.fb_getList(cri);
			ArrayList<GroupBoardVO> list = groupService.gb_getList(cri);
			int total = groupService.gb_getTotal(cri);
			PageVO pageVO = new PageVO(cri, total);
			
			for(int listCnt = 0;listCnt <list.size();listCnt++) {	
				//댓글 VO 선언
				GroupCommentsVO groupCommentsVO = new GroupCommentsVO();
				// 댓글 VO에 해당 게시물의 고유번호를 SET
				groupCommentsVO.setGroup_board_no(list.get(listCnt).getGroup_board_no());
				// 게시물 고유번호로 댓글 조회
				ArrayList<GroupCommentsVO> c_list = groupService.gc_getList( groupCommentsVO );
				// 댓글 리스트를 게시물 VO에 SET
				list.get(listCnt).setGroup_comments_list(c_list);
			}
			System.out.println("pageVO = "+pageVO);
			// 데이터 저장
			model.addAttribute("list", list);
			// 페이지네이션 저장 
			model.addAttribute("pageVO", pageVO);
			return "group/groupBoard";
		}
		
		// 글 등록 페이지
		@GetMapping("/groupBoardReg")
		public String groupBoardReg() {	
			
			return "group/groupBoardReg";
		}
		
		// 글 등록 기능 
		@PostMapping("/boardForm")
		public String boardForm(GroupBoardVO vo, RedirectAttributes RA,
							    HttpSession session) {
			//유저확인 (로그아웃 상태에서 글등록 안 들어가는지 확인 필요!!!!!!!!!!!!!!!!!!!!!!!!!!1)
			UserVO userVO = (UserVO)session.getAttribute("userVO");  
			if(userVO == null || userVO.getUser_no() != vo.getUser_no()) {
				return "redirect:/group/groupBoard";
			}
			// 실행
			int result = groupService.gb_regist(vo);	
			if(result == 1) { 
				RA.addFlashAttribute("msg", "등록되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
				}
			return "redirect:/group/groupBoard";
		}
		
		// 글 수정 (불러오기)
		@GetMapping("/groupBoardUdt")
		public String groupBoardUdt(GroupBoardVO vo, 
				@RequestParam("group_board_no") int group_board_no,
				@RequestParam("user_no")  int user_no,
				HttpSession session, Model model) {	
			// 실행
			GroupBoardVO gb_VO = groupService.gb_getUpdateList(group_board_no);
			model.addAttribute("gb_VO", gb_VO);
			return "group/groupBoardUdt";
		}
		
		//글 수정 (업데이트)
		@PostMapping("/updateForm")
		public String updateForm(GroupBoardVO vo, 
								RedirectAttributes RA) {
			// 실행
			int result = groupService.gb_update(vo);
			if(result == 1) { 
				RA.addFlashAttribute("msg", "수정되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
				}
			return "redirect:/group/groupBoard";
		}
		
		//글 삭제
		@GetMapping("/boardDelete")  
		public String boardDelete(GroupBoardVO vo,
								  @RequestParam("group_board_no") int group_board_no,
								  @RequestParam("user_no")  int user_no,
								  HttpSession session, RedirectAttributes RA) {	
			// 실행
			int result = groupService.gb_delete(vo);
			if(result == 1) { 
				RA.addFlashAttribute("msg", "삭제되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
				}
			return "redirect:/group/groupBoard";
		}
		
		//댓글 등록
		@PostMapping("/groupCommentForm2")
		public String mainCommentForm(GroupCommentsVO vo,
							  		    HttpSession session, RedirectAttributes RA) {
			//유저확인
			UserVO userVO = (UserVO)session.getAttribute("userVO");  
			if(userVO == null || userVO.getUser_no() != vo.getUser_no()) {
				System.out.println("세션없음....");
				return "redirect:/group/groupBoard";
			}
			//실행
			int result = groupService.gc_regist(vo);	
			if(result == 1) { 
				RA.addFlashAttribute("msg", "댓글이 등록되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "등록실패, 관리자에게 문의하세요.");
				}
			return "redirect:/group/groupBoard";
		}
		
		//댓글 삭제
		@GetMapping("/deleteComment")
		public String deleteComment(GroupCommentsVO vo,
						  		    HttpSession session,
						  		    RedirectAttributes RA) {
			//유저확인
//			UserVO userVO = (UserVO)session.getAttribute("userVO");  
//			if(userVO == null || userVO.getUser_no() != mainCommentsVO.getUser_no()) {
//				System.out.println("세션없음....");
//				return "redirect:/board/freeboard_main";
//			}
			//실행
			int result = groupService.gc_delete(vo);
			if(result == 1) { 
				RA.addFlashAttribute("msg", "삭제되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "삭제실패, 관리자에게 문의하세요.");
				}
			System.out.println(result);
			return "redirect:/group/groupBoard";
		}
		
		// 댓글 수정
		@PostMapping("/updateComment")
		public String freeboard_update(GroupCommentsVO vo,
			    @RequestParam("writer") int writer,
	  		    HttpSession session, Model model,
	  		    RedirectAttributes RA) {	
			//유저확인
			UserVO userVO = (UserVO)session.getAttribute("userVO");  
			if(userVO == null || userVO.getUser_no() != vo.getUser_no()) {
				System.out.println("세션없음....");
				return "redirect:/board/freeboard_main";
			}
			int result =groupService.gc_UpdateContents(vo);
			if(result == 1) { 
				RA.addFlashAttribute("msg", "수정되었습니다. ");
				}else { 
				RA.addFlashAttribute("msg", "수정실패, 관리자에게 문의하세요.");
				}
			System.out.println(result);
			return "redirect:/group/groupBoard";
		}
	
	
}


