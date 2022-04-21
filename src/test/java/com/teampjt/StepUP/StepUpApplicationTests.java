package com.teampjt.StepUP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teampjt.StepUP.command.UserVO;
import com.teampjt.StepUP.user.UserMapper;

@SpringBootTest
class StepUpApplicationTests {
	
//	@Autowired
//	UserMapper userMapper;
//
//	@Test
//	public void test01() { //가상데이터 만들기
//		
//		for(int i = 1; i <= 100; i++) {
//			
//			UserVO vo = UserVO.builder().email("test" + i + "@naver.com")
//										.password("test" + i)
//										.user_name("admin")
//										.interest("test" + i)
//										.build();
//			               
//			
//			userMapper.regist(vo);
//			
//		}
//	}

}
