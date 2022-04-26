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
public class FreeBoardVO {

	//자유게시판 글 고유 번호(자동증가값)
	private Integer free_board_no;
  
    //자유게시판 글 제목
    private String free_title;

	//자유게시판 글 작성자 고유번호
	private Integer user_no;
	
	//자유게시판 글 작성자 닉네임
	private String user_name;

    //자유게시판 글 내용
    private String free_content;

    //자유게시판 글 작성 일자
    private LocalDateTime free_regdate;
    
    private ArrayList<MainCommentsVO> main_comments_list;

}
