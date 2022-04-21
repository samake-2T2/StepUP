package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestVO {
	
	//신청 목록 번호(식별용)
	private Integer req_no;
	
	//스터디 그룹 번호 - 어디에 신청했는가(FK)
	private Integer group_no;
	
	//사용자 고유 번호 - 누가 신청했는가(FK)
	private Integer user_no;
	
	//사용자 닉네임
	private String user_name;
	
	//사용자 이메일
	private String email;
	
	//신청인의 수준
	private String req_level;
	
	//신청인의 한줄소개
	private String req_about_me;

}
