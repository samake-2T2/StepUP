package com.teampjt.StepUP.group;

import java.util.ArrayList;

import com.teampjt.StepUP.command.SearchCategoryVO;

public interface GroupService {

	//카테고리 조회
	public ArrayList<SearchCategoryVO> getCategory();
	
	//두 번째 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo);
}
