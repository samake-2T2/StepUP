package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.command.SearchCategoryVO;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	public GroupMapper groupMapper;
	
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
	public ArrayList<GroupNoticeVO> getNoticeList() {
		return groupMapper.getNoticeList();
	}

	@Override
	public GroupNoticeVO getNoticeDetail(int groupnotice_no) {
		return groupMapper.getNoticeDetail(groupnotice_no);
	}

	@Override
	public int updatecount(int groupnotice_no) {
		return groupMapper.updatecount(groupnotice_no);
	}

}
