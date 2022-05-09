package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupMemberVO {

	private Integer groupmember_no;
	
	private Integer group_no;
	
	private Integer user_no;
	
	private String user_name;
}
