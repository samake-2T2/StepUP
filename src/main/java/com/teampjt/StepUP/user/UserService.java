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
	
	public int getUser(UserVO vo);
	
	//그룹 신청인 목록 조회 메서드
	public ArrayList<RequestVO> getApplyList(int group_no);
  
	public int update(UserVO userVO);
  
	public UserVO login(UserVO vo);
	
	//아이디 중복조회
	public int emailserch(UserVO vo);

}
