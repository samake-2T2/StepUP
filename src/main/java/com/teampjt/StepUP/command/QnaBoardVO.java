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
public class QnaBoardVO {
	
	//QNA게시판 글 고유 번호(자동증가값)
	private Integer qna_board;
	
	//QNA게시판 글 제목
	private String qna_title;
	
	//QNA게시판 글 작성자(user_no)
	private String user_no;
	
	//QNA게시판 글 작성자(qna_writer)
	private String qna_writer;
	
	//QNA게시판 글 내용
	private String qna_content;
	
	//QNA게시판 글 작성 일자
	private LocalDateTime qna_regdate;

}
