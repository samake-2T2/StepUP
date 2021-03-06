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
public class GroupBoardVO {
	
	//그룹전용게시판 글 고유 번호(자동증가값)
	private Integer group_board_no;
  
    //그룹전용게시판 글 제목
    private String title;

	//그룹전용게시판 글 작성자 고유번호
	private Integer user_no;
	
	//그룹전용게시판 글 작성자 닉네임
	private String user_name;

    //그룹전용게시판 글 내용
    private String content;

    //그룹전용게시판 글 작성 일자
    private LocalDateTime regdate;
    
    //댓글 리스트
    private ArrayList<GroupCommentsVO> group_comments_list;
	
}
