package com.ezen.springdb.board.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.springdb.board.dto.BoardDTO;

public interface BoardListService {

	// 세세한 동작 하나하나씩 나눠서 지정해주는 것이 서비스
	
	BoardDTO clickTitle(Integer board_id, HttpServletRequest req, HttpServletResponse resp);
	
//	Member clickWriterName();
	
}
