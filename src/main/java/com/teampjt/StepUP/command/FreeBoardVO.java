package com.teampjt.StepUP.command;

import java.time.LocalDateTime;

public class FreeBoardVO {

	//자유게시판 글 고유 번호(자동증가값)
	private Integer free_board;

	//자유게시판 글 제목
	private String free_title;

	//자유게시판 글 작성자(user_name)
	private String user_name;

	//자유게시판 글 내용
	private String free_content;

	//자유게시판 글 작성 일자
	private LocalDateTime free_regdate;

}
