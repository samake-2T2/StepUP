package com.teampjt.StepUP.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupMemberVO;
import com.teampjt.StepUP.command.GroupNoticeVO;
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
							Model model) {
		
		StudyGroupVO vo = groupService.getStudyGroupDetail(group_no);
		
		model.addAttribute("SGvo", vo);
		
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
		System.out.println(list1.toString());
		ArrayList<Integer> list2 = groupService.getMyGroupNoList2(user_no);
		System.out.println(list2.toString());
		
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
	public String request_ok(GroupMemberVO vo,
							 RedirectAttributes RA) {
		
		System.out.println(vo.toString());
		
		int result = groupService.requestOk(vo);
		 
		if(result == 1) { 
			RA.addFlashAttribute("msg", vo.getUser_name() + "님이 등록 되었습니다."); 
		} else { 
			RA.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요.");
		}
		return "redirect:/group/groupRegList";
	}
	//신청 거절
	@PostMapping("/request_no")
	public String request_no(RequestVO vo,
			                 RedirectAttributes RA) {
		
		System.out.println(vo.toString());
		
		int result = groupService.requestNo(vo);
		System.out.println(result);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "가입이 거절되었습니다.");
		} else {
			RA.addFlashAttribute("msg", "가입 거절에 실패했습니다.");
		}
		
		return "redirect:/group/groupRegList";
		
	}

	//공지목록페이지
	@GetMapping("/groupNotice")
	public String groupNotice( Model model, Criteria cri, StudyGroupVO vo ) {



		ArrayList<GroupNoticeVO> noticeList = groupService.getNoticeList(cri);
		int total = groupService.GNgetTotal();
		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("vo", vo);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pageVO", pageVO);

		return "group/groupNotice";
	}

	//공지상세보기
	@GetMapping("/groupNoticeDetail")
	public String groupNoticeDetail(@RequestParam("groupnotice_no") int groupnotice_no,
			Model model) {

		GroupNoticeVO gnVO = groupService.getNoticeDetail(groupnotice_no);
		model.addAttribute("gnVO", gnVO);
		model.addAttribute("move", groupService.movePage(groupnotice_no));

		groupService.updatecount(groupnotice_no);

		return "group/groupNoticeDetail";
	}

	//공지등록페이지
	@GetMapping("/groupNoticeReg")
	public String groupNoticeReg() {
		return "group/groupNoticeReg";
	}

	//그룹공지등록 폼
	@PostMapping("/groupNoticeForm")
	public String groupNoticeForm(GroupNoticeVO gnVO,
			RedirectAttributes RA) {

		
		
		int result = groupService.noticeRegist(gnVO);

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
			Model model) {
		
		
		
		GroupNoticeVO gnVO = groupService.getNoticeModify(groupnotice_no);
		model.addAttribute("gnVO", gnVO);
		
		
		return "group/groupNoticeModify";
	}

	//공지수정 폼
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(GroupNoticeVO gnVO,
			RedirectAttributes RA, Errors errors, Model model) {
		
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
	public String noticeDelete(@RequestParam("groupnotice_no") int groupnotice_no) {

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
		
		
		
		model.addAttribute("groupVO", groupVO);
		model.addAttribute("mtotal", mtotal+1);
		
		System.out.println(groupVO.toString());
		
		return "group/groupDetail";
	}

	
	//댓글 등록폼
	@PostMapping("/groupCommentForm")
	public String groupCommentForm(GroupDetailCommentVO gdcVO,
								  RedirectAttributes RA) {
	
		

		int result = groupService.commentRegist(gdcVO);

		if(result == 1) {
			RA.addFlashAttribute("msg", "댓글이 등록되었습니다"); 
		}else {
			RA.addFlashAttribute("msg", "댓글등록이 실패하였습니다");
		}
	
		return "redirect:/group/groupDetail";
	}


	//댓글 수정폼
	@PostMapping("/groupCommentUpdate")
	public String groupCommentUpdate(GroupDetailCommentVO gdcVO,
									 RedirectAttributes RA) {
		
		
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
									 RedirectAttributes RA) {
		
		
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
	public int nameChk(StudyGroupVO vo) {
	
		return groupService.nameChk(vo);
	}

	// 그룹 생성폼
	@PostMapping("/group_reg_form")
	public String group_reg_form(StudyGroupVO vo,
			RedirectAttributes RA,
			@RequestParam("file") MultipartFile f) {
		
		int result = groupService.nameChk(vo);
		
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
		
		if(result < 1) { // 가입한 이력이 없는경우
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
		} else { // 이미 가입한 이력이 있는 경우
			
				RA.addFlashAttribute("msg", "가입되어있는 그룹입니다");
			
			return "redirect:/main";
		}
	}
	
	// 그룹 투두리스트 등록 폼
	@PostMapping("/todoReg")
	public String todoReg(ToDoListVO vo,
						  Model model) {
		
		groupService.toDoListReg(vo);
		
		ArrayList<ToDoListVO> list = groupService.getToDoList(vo);
		model.addAttribute("list", list);
		
		return "redirect:/group/groupMain";
	}
	
	
}


