package com.teampjt.StepUP.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
}
