package com.teampjt.StepUP.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.RequestVO;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
import com.teampjt.StepUP.util.Criteria;

@Mapper
public interface UserMapper {
  
	public int userRegist(UserVO vo); //회원등록
	
	public int registFile(userUploadVO vo); //파일 등록
	
	public int userDelete(int user_no);
	
	//그룹 신청인 목록 조회 메서드
	public ArrayList<RequestVO> getApplyList(int group_no);
	
	public int update(UserVO userVO);
  
	public UserVO login(UserVO vo);
	
	public ArrayList<GroupDetailCommentVO> getGroupList();
	
	public UserVO emailserch(UserVO vo);


	
	
}
