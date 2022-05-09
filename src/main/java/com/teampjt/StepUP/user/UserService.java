package com.teampjt.StepUP.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.teampjt.StepUP.command.GroupDetailCommentVO;
import com.teampjt.StepUP.command.RequestVO;
import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.command.userUploadVO;
import com.teampjt.StepUP.util.Criteria;

public interface UserService {
	
	public int userRegist(UserVO vo); //회원가입
	
	public int registFile(UserVO vo, MultipartFile f); //파일 등록

	public int userDelete(int user_no);
	//그룹 신청인 목록 조회 메서드
	public ArrayList<RequestVO> getApplyList();
  
	public int update(UserVO userVO);
  
	public UserVO login(UserVO vo);
	
	

}
