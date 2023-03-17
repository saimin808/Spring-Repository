package com.ezen.springdb.dto;

import lombok.Data;

@Data
public class Board {

	private Integer board_id;
	private String writer;
	private String write_pw;
	private String write_type;
	private String write_title;
	private String write_content;
	private String write_date;
	private Integer write_view;
	private Integer write_recommand;
	private Integer write_not_recommand;
	
}
