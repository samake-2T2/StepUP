package com.teampjt.StepUP.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.FreeBoardVO;
import com.teampjt.StepUP.command.MainCommentsVO;
import com.teampjt.StepUP.util.Criteria;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public int fb_regist(FreeBoardVO freeBoardVO) {
		return boardMapper.fb_regist(freeBoardVO);
	}

	@Override
	public ArrayList<FreeBoardVO> fb_getList(Criteria cri) {
		System.out.println("cri="+cri);
		return boardMapper.fb_getList(cri);
	}
	
	@Override
	public int fb_getTotal(Criteria cri) {
		
		return boardMapper.fb_getTotal(cri);
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

	@Override
	public int mc_regist(MainCommentsVO mainCommentsVO) {
		return boardMapper.mc_regist(mainCommentsVO);
	}

	@Override
	public ArrayList<MainCommentsVO> mc_getList(MainCommentsVO mainCommentsVO) {
		return boardMapper.mc_getList(mainCommentsVO);
	}

	@Override
	public int mc_delete(MainCommentsVO mainCommentsVO) {
		return boardMapper.mc_delete(mainCommentsVO);
	}

	@Override
	public int mc_UpdateContents(MainCommentsVO mainCommentsVO) {

		return boardMapper.mc_UpdateContents(mainCommentsVO);
	}





















	
}
