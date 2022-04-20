package com.teampjt.StepUP.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public int userDelete(int user_no);
}
