package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

	// 회원번호(식별용)
	private Integer user_no;
	
	// 이메일(아이디 겸용)
	private String email;
	
	// 비밀번호
	private String password;
	
	// 닉네임
	private String user_name;
	
	// 관심분야
	private String interest;
	
}
