package com.teampjt.StepUP.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.teampjt.StepUP.command.GroupNoticeVO;
import com.teampjt.StepUP.group.GroupMapper;

class UserControllerTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Autowired
	GroupMapper groupMapper;
	
	@Test
	public void test02() {
		
		for(int i = 1; i <= 300; i++) {
			
			GroupNoticeVO gnVO = GroupNoticeVO.builder()
					.groupnotice_title("test" + i)
					.groupnotice_content("test" + i)
					.groupnotice_writer("홍길동")
					.groupnotice_count(i)
					.build();
			
			groupMapper.noticeRegist(gnVO);
		}
		
	}
}
