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
public class GroupDetailCommentVO {

	
//	##댓글 테이블 
//	create table group_detail_comment (
//		comment_no int primary key auto_increment,
//	    user_name varchar(10) not null,
//	    comment_regdate timestamp default now(),
//	    comment_content varchar(500) not null,
//	    user_profile int,
//	    user_no int
//	);  
	
	
	private Integer comment_no;
	private String user_name;
	private LocalDateTime comment_regdate;
	private String comment_content;
	private Integer user_profile;
	private Integer user_no;
	
	
	
	
	
}
