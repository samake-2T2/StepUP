package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyGroupVO {
	
	//스터디 그룹 번호(식별용)
	private Integer group_no;
	
	//스터디 그룹명
	private String group_name;
	
	//스터디 그룹 설명
	private String group_content;
	
	//스터디 그룹원 규모
	private Integer group_participant_amount;
	
	// 스터디 그룹장 회원번호
	private Integer group_leader_no;
	
	//스터디 그룹장 이름
	private String group_leader_name;
	
	//스터디 그룹 프로필 사진 이름
	private String group_filename;
	
	//스터디 그룹 프로필 사진 경로
	private String group_filepath;
	
	//스터디 그룹 프로필 사진 저장명(랜덤값)
	private String group_fileuuid;
	
	//카데고리 대분류
	private String category_parent_name;
	
	//카테고리 소분류
	private String category_child_name;

}
