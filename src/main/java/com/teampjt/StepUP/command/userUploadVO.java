package com.teampjt.StepUP.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userUploadVO {
	
//  ##업로드 테이블
//	create table user_upload (
//			   upload_no int primary key auto_increment,
//			    filename varchar(100) not null,
//			    filepath varchar(100) not null,
//			    uuid varchar(50) not null,
//			    user_no int not null
//			);

	// 업로드 번호(식별용)
	private Integer upload_no;
	
	// 실제파일명
	private String filename;
	
	// 파일경로(ex. 220407등의 기능구현시 설정한 폴더명?)
	private String filepath;
	
	// 파일 이름앞에 들어가는 랜덤값
	private String uuid;
	
	// 해당유저의 프로필 사진임을 확인하기 위한 참조키
	private Integer user_no;
	
}
