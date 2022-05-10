package com.teampjt.StepUP.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampjt.StepUP.command.FreeBoardVO;
import com.teampjt.StepUP.command.MainCommentsVO;
import com.teampjt.StepUP.command.QnaBoardVO;
import com.teampjt.StepUP.command.QnaCommentsVO;
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

	//*****qna게시판*****
	@Override
	public int qna_regist(QnaBoardVO qnaBoardVO) {

		return boardMapper.qna_regist(qnaBoardVO);
	}

	@Override
	public ArrayList<QnaBoardVO> qna_getList(Criteria cri) {

		return boardMapper.qna_getList(cri);
	}

	@Override
	public int qna_getTotal(Criteria cri) {

		return boardMapper.qna_getTotal(cri);
	}

	@Override
	public QnaBoardVO qna_getUpdateList(int qna_board_no) {

		return boardMapper.qna_getUpdateList(qna_board_no);
	}

	@Override
	public int qna_update(QnaBoardVO qnaBoardVO) {

		return boardMapper.qna_update(qnaBoardVO);
	}

	@Override
	public int qna_delete(QnaBoardVO qnaBoardVO) {

		return boardMapper.qna_delete(qnaBoardVO);
	}

	@Override
	public int qc_regist(QnaCommentsVO qnaCommentsVO) {

		return boardMapper.qc_regist(qnaCommentsVO);
	}

	@Override
	public ArrayList<QnaCommentsVO> qc_getList(QnaCommentsVO qnaCommentsVO) {

		return boardMapper.qc_getList(qnaCommentsVO);
	}

	@Override
	public int qc_delete(QnaCommentsVO qnaCommentsVO) {

		return boardMapper.qc_delete(qnaCommentsVO);
	}

	@Override
	public int qc_UpdateContents(QnaCommentsVO qnaCommentsVO) {

		return boardMapper.qc_UpdateContents(qnaCommentsVO);
	}
	
	
























	
}
