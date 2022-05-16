package com.teampjt.StepUP.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.group.GroupService;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.util.PageVO;

@Controller
public class MainController {
	
	@Autowired
	public GroupService groupService;
	
	@GetMapping("/main")
	public void main(Model model, 
			         Criteria cri //,
//			         @RequestParam(value="parent_name", required = false) String parent_name,
//			         @RequestParam(value="child_name", required = false) String child_name,
//			         @RequestParam(value="group_name", required = false) String group_name
			         ) {
		
//		cri.setSearchCategoryParent(parent_name);
//		cri.setSearchCategoryChild(child_name);
//		cri.setSearchKeyword(group_name);
		
		//System.out.println(cri.toString());
		
		//페이징처리
		ArrayList<StudyGroupVO> list = groupService.getGroupList(cri);
		int total = groupService.getGroupTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);
		
		//스터디 그룹 데이터 저장
		model.addAttribute("list", list);
		
		//검색 결과 데이터 저장
		model.addAttribute("cri", cri);
		
		//페이지네이션 저장
		model.addAttribute("pageVO", pageVO);
		
	}
	

	@ResponseBody
	@PostMapping("/mainAjax")
	public HashMap<String, Object> main2(Model model, 
			         					 Criteria cri,
			         					 @RequestBody HashMap<String, String> map
										 ) {
		
//		System.out.println(map.toString());
		
		cri.setSearchCategoryParent(map.get("parent_name"));
		cri.setSearchCategoryChild(map.get("child_name"));
		cri.setSearchKeyword(map.get("group_name"));
		
		
		//System.out.println(cri.toString());
		
		//페이징처리
		ArrayList<StudyGroupVO> list = groupService.getGroupList(cri);
		int total = groupService.getGroupTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);
		
		//스터디 그룹 데이터 저장
		
		//검색 결과 데이터 저장
		
		//페이지네이션 저장
		
		//System.out.println(list.toString());
		
		HashMap<String, Object> maps = new HashMap<>();
		maps.put("list", list);
		maps.put("cri", cri);
		maps.put("pageVO", pageVO);
		
		// System.out.println(maps.toString());
		
		return maps;
	}
	
}
