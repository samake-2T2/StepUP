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

	public ArrayList<UserVO> getUserList(Criteria cri) {

		return userMapper.getUserList(cri);
	}

	@Override
	public int getUserTotal() {

		return userMapper.getUserTotal();
  }
  
  @Override
	public UserVO login(UserVO vo) {
		
		return userMapper.login(vo);
	}

}
