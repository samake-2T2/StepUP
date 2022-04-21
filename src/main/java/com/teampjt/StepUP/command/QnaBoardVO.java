package com.teampjt.StepUP.command;

import java.time.LocalDateTime;

public class QnaBoardVO {
	
	//QNA게시판 글 고유 번호(자동증가값)
	private Integer qna_board;
	
	//QNA게시판 글 제목
	private String qna_title;
	
	//QNA게시판 글 작성자(user_name)
	private String user_name;
	
	//QNA게시판 글 내용
	private String qna_content;
	
	//QNA게시판 글 작성 일자
	private LocalDateTime qna_regdate;

}
