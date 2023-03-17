package com.ezen.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.springdb.board.dto.BoardDTO;

public interface BoardMapper {

	@Select("SELECT * FROM boards WHERE board_id=#{board_id}")
	BoardDTO get(Integer board_id);
	
	@Select("SELECT * FROM boards ORDER BY write_date DESC")
	List<BoardDTO> getAll();
	
	@Insert("INSERT INTO boards VALUES(board_id_seq.nextval,"
			+ " #{writer}, #{write_pw}, #{write_type}, #{write_title}, #{write_content}, sysdate,0,0,0)")
	Integer add(BoardDTO board);
	
	@Update(value = "UPDATE boards SET write_content=#{write_content} WHERE board_id=#{board_id}")
	Integer update(BoardDTO board);
	
	@Delete("DELETE FROM boards WHERE board_id=#{board_id}")
	Integer delete(Integer board_id);
}
