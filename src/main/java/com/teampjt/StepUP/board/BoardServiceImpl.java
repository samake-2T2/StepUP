package com.teampjt.StepUP.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.FreeBoardVO;
import com.teampjt.StepUP.command.UserVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public int fb_regist(FreeBoardVO freeBoardVO) {
		return boardMapper.fb_regist(freeBoardVO);
	}

	@Override
	public ArrayList<FreeBoardVO> fb_getList() {
		return boardMapper.fb_getList();
	}

	@Override
	public FreeBoardVO fb_getUpdateList(int free_board_no) {
		return boardMapper.fb_getUpdateList(free_board_no);
	}

	@Override
	public int fb_update(FreeBoardVO freeBoardVO) {
		return boardMapper.fb_update(freeBoardVO);
	}

	@Override
	public int fb_delete(FreeBoardVO freeBoardVO) {
		return boardMapper.fb_delete(freeBoardVO);
	}














	
}
