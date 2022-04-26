package com.teampjt.StepUP.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.group.GroupService;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.util.PageVO;

@Controller
public class MainController {
	
	@Autowired
	public GroupService groupService;
	
	
	@GetMapping("/main")
	public void main(Model model, Criteria cri) {
		
		System.out.println(cri.toString());
		
		//페이징처리
		ArrayList<StudyGroupVO> list = groupService.getGroupList(cri);
		int total = groupService.getGroupTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);
		
		//데이터 저장
		model.addAttribute("list", list);
		
		//페이지네이션 저장
		model.addAttribute("pageVO", pageVO);
		
		System.out.println(cri.toString());
		
		
	}
	
}
