package com.teampjt.StepUP.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teampjt.StepUP.command.GroupNoticeVO;
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
	public String groupApplication() {
		return "group/groupApplication";
	}
	
	@GetMapping("/groupMain")
	public String groupMain() {
		return "group/groupMain";
	}
	
	@GetMapping("/groupRegist")
	public String groupRegist() {
		return "group/groupRegist";
	}
	
	//그룹 신청인 목록 조회(그룹장이 확인)
	@GetMapping("/groupRegList")
	public String groupRegList(Model model, Criteria cri) {
		
		//페이징 처리
		ArrayList<UserVO> list = userService.getApplyList(cri);
		int total = userService.getApplyTotal();
		
		PageVO pageVO = new PageVO(cri, total);
		
		//데이터 저장
		model.addAttribute("applylist", list);
		
		//페이지네이션 저장
		model.addAttribute("pageVO", pageVO);
		
		
		
		
		return "group/groupRegList";
	}
	
	@GetMapping("/groupDetail")
	public String groupDetail() {
		return "group/groupDetail";
	}
	
	//공지목록페이지
	@GetMapping("/groupNotice")
	public String groupNotice(Model model, Criteria cri) {
		
		ArrayList<GroupNoticeVO> noticeList = groupService.getNoticeList(cri);
		int total = groupService.getTotal();
		PageVO pageVO = new PageVO(cri, total);
		
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
							   RedirectAttributes RA) {
		
		int result = groupService.noticeUpdate(gnVO);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "공지가 수정되었습니다");
		}else {
			RA.addFlashAttribute("msg", "공지가 수정되지않았습니다");
		}
		
		return "redirect:/group/groupNotice";
	}
	
	//공지 삭제폼
	@PostMapping("/noticeDelete")
	public String noticeDelete(@RequestParam("groupnotice_no") int groupnotice_no) {
		
		int result = groupService.noticeDelete(groupnotice_no);
		
		return "redirect:/group/groupNotice";
	}
	
	
	
}
