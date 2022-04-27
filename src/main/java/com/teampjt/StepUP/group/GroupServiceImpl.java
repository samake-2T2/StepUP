package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.SearchCategoryVO;
import com.teampjt.StepUP.command.StudyGroupVO;
import com.teampjt.StepUP.util.Criteria;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	public GroupMapper groupMapper;
	
	@Override
	public ArrayList<StudyGroupVO> getGroupList(Criteria cri) {

		return groupMapper.getGroupList(cri);
	}
	
	@Override
	public int getGroupTotal(Criteria cri) {

		return groupMapper.getGroupTotal(cri);
	}
	
	@Override
	public ArrayList<SearchCategoryVO> getCategory() {

		return groupMapper.getCategory();
	}

	@Override
	public ArrayList<SearchCategoryVO> getCategoryChild(SearchCategoryVO vo) {

		return groupMapper.getCategoryChild(vo);
	}
	
	@Override
	public int noticeRegist(GroupNoticeVO gnVO) {
		return groupMapper.noticeRegist(gnVO);
	}

	@Override
	public ArrayList<GroupNoticeVO> getNoticeList(Criteria cri) {
		return groupMapper.getNoticeList(cri);
	}

	@Override
	public GroupNoticeVO getNoticeDetail(int groupnotice_no) {
		return groupMapper.getNoticeDetail(groupnotice_no);
	}

	@Override
	public int updatecount(int groupnotice_no) {
		return groupMapper.updatecount(groupnotice_no);
	}

	@Override
	public GroupNoticeVO getNoticeModify(int groupnotice_no) {
		return groupMapper.getNoticeModify(groupnotice_no);
	}

	@Override
	public int noticeUpdate(GroupNoticeVO gnVO) {
		return groupMapper.noticeUpdate(gnVO);
	}

	@Override
	public int noticeDelete(int groupnotice_no) {
		return groupMapper.noticeDelete(groupnotice_no);
	}

	@Override
	public int getTotal() {
		return groupMapper.getTotal();
	}		

	public ArrayList<GroupDetailCommentVO> getComment() {
		
		return groupMapper.getComment();
	}

	@Override
	public int commentRegist(GroupDetailCommentVO gdcVO) {
		
		return groupMapper.commentRegist(gdcVO);
	}

	@Override
	public ArrayList<GroupDetailCommentVO> getGroupCommentList() {
		
		return groupMapper.getGroupCommentList();
}
  
  @Override
	public GroupNoticeVO movePage(int groupnotice_no) {
		return groupMapper.movePage(groupnotice_no);
	}

  @Override
  	public int commentUpdate(GroupDetailCommentVO gdcVO) {

	  	return groupMapper.commentUpdate(gdcVO);
}




  	
}
