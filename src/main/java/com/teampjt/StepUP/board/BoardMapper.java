package com.teampjt.StepUP.board;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.teampjt.StepUP.command.FreeBoardVO;


@Mapper
public interface BoardMapper {
	
	//글 등록
	public int fb_regist(FreeBoardVO freeBoardVo);
	
	//글 목록 불러오기
	public ArrayList<FreeBoardVO> fb_getList();
	
	//글 내용 불러오기
	public FreeBoardVO fb_getUpdateList(int free_board_no);
	
	//글 내용 수정하기
	public int fb_update(FreeBoardVO freeBoardVO);
	
	//글 삭제
	public int fb_delete(FreeBoardVO freeBoardVO);
}
