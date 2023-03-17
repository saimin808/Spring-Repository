package sai.pork.springboard.mapper;

import java.util.List;

import sai.pork.springboard.dto.Board;

public interface BoardXmlMapper {
	
	Board getBoard(Integer board_id);
	
	Integer updateView(Integer board_id);
	
	List<Board> getBoards();
	
	Integer writeBoard(Board board);
	
	Integer modifyBoard(Board board);
	
	Integer deleteBoard(Integer board_id);
	
	Integer recommendBoard(Integer board_id);

	Integer notRecommendBoard(Integer board_id);
}
