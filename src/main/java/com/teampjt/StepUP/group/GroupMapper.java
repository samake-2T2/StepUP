package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupNoticeVO;

@Mapper
public interface GroupMapper {

	public ArrayList<GroupDetailCommentVO> getComment();

	public int commentRegist(GroupDetailCommentVO gdcVO); // 글 등록

	// 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategory();

	// 두 번째 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo);

	public int noticeRegist(GroupNoticeVO gnVO); // 공지등록

	public ArrayList<GroupNoticeVO> getNoticeList(); // 공지목록

	public GroupNoticeVO getNoticeDetail(int groupnotice_no); // 공지상세보기

	public int updatecount(int groupnotice_no); // 조회수
}
