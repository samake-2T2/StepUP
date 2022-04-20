package com.teampjt.StepUP.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int userJoin(UserVO vo) {
		
		return userMapper.userJoin(vo);
	}

	
	 
}
