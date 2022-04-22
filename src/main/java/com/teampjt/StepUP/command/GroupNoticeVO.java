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
public class GroupNoticeVO {

	private Integer groupnotice_no;
	private String groupnotice_title;
	private String groupnotice_writer;
	private String groupnotice_content;
	private LocalDateTime groupnotice_regdate;
	private Integer groupnotice_count;
	
}
