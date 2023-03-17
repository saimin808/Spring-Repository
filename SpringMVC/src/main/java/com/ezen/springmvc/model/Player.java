package com.ezen.springmvc.model;

import lombok.Data;

@Data
public class Player {

	private String player;
	private Integer gameCnt;
	private Integer win;
	private Integer draw;
	private Integer lose;
	
	@Override
	public String toString() {
		return String.format("%d�� %d�� %d�� %d��", gameCnt, win, draw, lose);
	}
	
}
