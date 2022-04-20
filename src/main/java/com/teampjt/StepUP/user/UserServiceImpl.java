package com.teampjt.StepUP.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int userDelete(int user_no) {
		
		return userMapper.userDelete(user_no);
	}

}
