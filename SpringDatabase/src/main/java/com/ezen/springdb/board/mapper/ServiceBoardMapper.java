package com.ezen.springdb.board.mapper;

import java.util.List;

import com.ezen.springdb.board.dto.BoardDTO;

// Mapper는 테이블 또는 DTO 단위로 작성
public interface ServiceBoardMapper {

	BoardDTO get(Integer board_id);
	
//	List<Board> getAll();
	
	Integer plusView(Integer board_id);
}
