package com.teampjt.StepUP.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
	private Integer qna_board_no;
	
	//QNA게시판 글 제목
	private String qna_title;
	
	//QNA게시판 글 작성자(user_no)
	private Integer user_no;
	
	//자유게시판 글 작성자 닉네임
	private String user_name;
	
	//QNA게시판 글 내용
	private String qna_content;
	
	//QNA게시판 글 작성 일자
	private LocalDateTime qna_regdate;
	
	//댓글 리스트
    private ArrayList<QnaCommentsVO> qna_comments_list;

}
