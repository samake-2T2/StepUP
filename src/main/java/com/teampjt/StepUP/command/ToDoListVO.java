package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoListVO {
	
	// 구분번호
	private Integer todo_no;
	
	// 날짜
	private String regdate;
	
	// 장소
	private String place;
	
	// 내용
	private String content;
	
	// 참조용 그룹번호
	private Integer group_no;
	
}
