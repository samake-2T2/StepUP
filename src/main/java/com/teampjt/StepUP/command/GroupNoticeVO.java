package com.teampjt.StepUP.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupNoticeVO {
	
	private Integer groupnotice_no;
	
	@NotBlank(message = "제목을 입력해주세요")
	private String groupnotice_title;
	
	private String groupnotice_writer;
	
	@NotNull(message = "내용을 입력해주세요")
	private String groupnotice_content;
	
	private LocalDateTime groupnotice_regdate;
	private Integer groupnotice_count;
	
	private Integer group_no;
	
	//이전글 다음글
	private int next;
	private int prev;
	private String nexttitle;
	private String prevtitle;
}
