package com.teampjt.StepUP.group;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.GroupNoticeVO;

@Service("/groupService")
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupMapper groupMapper;
	
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
