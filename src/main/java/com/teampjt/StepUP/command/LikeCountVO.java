package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCountVO {
	
	// 좋아요 번호
	private int like_no;
	
	// 좋아요 누른 유저의 식별번호
	private int user_no;
	
	// 좋아요 누른 그룹 번호
	private int group_no;
	
}
