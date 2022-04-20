package com.teampjt.StepUP.user;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.UserVO;

@Mapper
public interface UserMapper {

	public int update(UserVO userVO);
	
}
