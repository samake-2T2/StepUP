package com.teampjt.StepUP.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
import com.teampjt.StepUP.util.Criteria;

@Mapper
public interface UserMapper {
  
	public int regist(UserVO vo); //등록
	
	public int registFile(userUploadVO vo); //파일 등록
	
	public int userDelete(int user_no);
	
	//그룹 신청인 목록 조회 메서드
	public ArrayList<UserVO> getApplyList(Criteria cri);
	
	//그룹 신청인 전체 인원 수
	public int getApplyTotal();


	public int userJoin(UserVO vo);

	public int update(UserVO userVO);
  
	public UserVO login(UserVO vo);

	
	
}
