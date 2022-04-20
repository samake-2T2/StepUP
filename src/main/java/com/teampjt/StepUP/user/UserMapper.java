package com.teampjt.StepUP.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.UserVO;

import com.teampjt.StepUP.util.Criteria;

@Mapper
public interface UserMapper {
	
	//그룹 신청인 목록 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri);
	
	//그룹 신청인 전체 인원 수
	public int getUserTotal();

	public int update(UserVO userVO);
  
	public UserVO login(UserVO vo);
	
}
