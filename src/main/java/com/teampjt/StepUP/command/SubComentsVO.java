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
public class SubComentsVO {

		// 서브 댓글 고유 번호 (자동증가값)
		private Integer sub_coment_no;
		
		// 서브 댓글 작성자 고유번호
		private Integer user_no;
		
		// 서브 댓글 글 작성자 닉네임
		private String user_name;
		
		// 서브 댓글 내용
		private String sub_coment_contents;
		
		// 서브 댓글 작성 시간
		private LocalDateTime sub_coment_regdate;
		
		// 서브 댓글 수정 시간 
		private LocalDateTime sub_coment_updatedate;
		
		private int free_board_no;
		
		private int coment_no;
		
}
