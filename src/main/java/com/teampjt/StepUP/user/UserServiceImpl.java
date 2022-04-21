package com.teampjt.StepUP.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.UserVO;

import com.teampjt.StepUP.util.Criteria;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int userJoin(UserVO vo) {
		
		return userMapper.userJoin(vo);
	}

	
	 
  @Override
	public int userDelete(int user_no) {
		
		return userMapper.userDelete(user_no);
  }
  
  @Override
	public int update(UserVO userVO) {
		return userMapper.update(userVO);
  }

  @Override
	public ArrayList<UserVO> getApplyList(Criteria cri) {

		return userMapper.getApplyList(cri);
	}

	@Override
	public int getApplyTotal() {

		return userMapper.getApplyTotal();
  }
  
  @Override
	public UserVO login(UserVO vo) {
		
		return userMapper.login(vo);
	}
}
