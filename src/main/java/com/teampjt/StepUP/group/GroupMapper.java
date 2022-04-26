package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.util.Criteria;
import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupNoticeVO;

@Mapper
public interface GroupMapper {
	
	//메인화면 스터디그룹 조회메서드
	public ArrayList<StudyGroupVO> getGroupList(Criteria cri); 
	
	//전체 그룹 조회메서드
	public int getGroupTotal(Criteria cri);

	public ArrayList<GroupDetailCommentVO> getComment();

	public int commentRegist(GroupDetailCommentVO gdcVO); // 글 등록

	// 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategory();

	// 두 번째 카테고리 조회
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo);
	
	public int noticeRegist(GroupNoticeVO gnVO); //공지등록
  
	public ArrayList<GroupNoticeVO> getNoticeList(Criteria cri); //공지목록
  
	public GroupNoticeVO getNoticeDetail(int groupnotice_no); //공지상세보기
  
	public int updatecount(int groupnotice_no); //조회수
  
	public GroupNoticeVO getNoticeModify(int groupnotice_no); //수정페이지
  
	public int noticeUpdate(GroupNoticeVO gnVO);//수정
  
	public int noticeDelete(int groupnotice_no); //공지삭제
  
	public int getTotal(); //전체게시글
	
	public ArrayList<GroupDetailCommentVO> getGroupList();

	public GroupNoticeVO movePage(int groupnotice_no); //이전글,다음글

}
