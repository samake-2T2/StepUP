package com.teampjt.StepUP.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupCommentsVO {
	
	// 그룹게시판 댓글 고유 번호 (자동증가값)
	private Integer comment_no;
	
	// 그룹게시판 댓글 작성자 고유번호
	private Integer user_no;
	
	// 그룹게시판 댓글 글 작성자 닉네임
	private String user_name;
	
	// 그룹게시판 댓글 내용
	private String comment_contents;
	
	// 그룹게시판 댓글 작성 시간
	private LocalDateTime comment_regdate;
	
	private Integer group_board_no;
	
	private String filepath;
	
}
