package com.teampjt.StepUP.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.group.GroupService;

@RestController
public class AjaxController {

	@Autowired
	public GroupService groupService;
	
	@GetMapping("/getCategory")
	public ArrayList<SearchCategoryVO> getCategory() {
		
		ArrayList<SearchCategoryVO> list = groupService.getCategory();
		
		
		return list;
	}
	
	//두 번째 카테고리
	@GetMapping("/getCategoryChild/{category_group_id}/{category_lv}/{category_detail_lv}")
	public ArrayList<SearchCategoryVO> getCategoryChild(@PathVariable("category_group_id") String category_group_id,
														@PathVariable("category_lv") int category_lv,
														@PathVariable("category_detail_lv") int category_detail_lv) {
		
		System.out.println(category_group_id);
		System.out.println(category_lv);
		System.out.println(category_detail_lv);
		System.out.println("-------------------");
		
		
		//마이바티스 전달을 위해 vo에 저장
		SearchCategoryVO vo = SearchCategoryVO.builder()
				                              .category_group_id(category_group_id)
				                              .category_lv(category_lv)
				                              .category_detail_lv(category_detail_lv)
				                              .build();
		
		ArrayList<SearchCategoryVO> list = groupService.getCategoryChild(vo);
		
		return list;
	}
			
		
	
}
