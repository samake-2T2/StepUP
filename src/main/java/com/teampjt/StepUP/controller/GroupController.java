package com.teampjt.StepUP.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.user.UserService;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.util.PageVO;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	public UserService userService;

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
		ArrayList<UserVO> list = userService.getUserList(cri);
		int total = userService.getUserTotal();
		
		PageVO pageVO = new PageVO(cri, total);
		
		//데이터 저장
		model.addAttribute("userlist", list);
		
		//페이지네이션 저장
		model.addAttribute("pageVO", pageVO);
		
		
		
		
		return "group/groupRegList";
	}
	
	@GetMapping("/groupDetail")
	public String groupDetail() {
		return "group/groupDetail";
	}
	
}
