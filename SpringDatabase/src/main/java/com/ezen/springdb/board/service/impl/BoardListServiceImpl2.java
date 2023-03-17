package com.ezen.springdb.board.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springdb.board.dto.BoardDTO;
import com.ezen.springdb.board.mapper.ServiceBoardMapper;
import com.ezen.springdb.board.service.BoardListService;

@Service
public class BoardListServiceImpl2 implements BoardListService {

	@Autowired
	ServiceBoardMapper board_mapper;
	
	@Override
	public BoardDTO clickTitle(Integer board_id, HttpServletRequest req, HttpServletResponse resp) {
		
		Cookie[] cookies = req.getCookies();
		List<String> idList = null;
		boolean viewed = false;
		Cookie viewCookie = null;
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if (cookie.getName().equals("viewed")) {
					viewCookie = cookie;
					
					// "33/3456/44/11/55/.." 쿠키의 용량은 4096bytes까지
					String ids = viewCookie.getValue();
					
					// split한 배열을 리스트로 만들어 주기
					String[] splited = ids.split("/");
					idList = Arrays.asList(splited);
					viewed = idList.contains(board_id.toString());
				}
			}
		}
		
		if(!viewed) {
			// 조회수는 올라가야된다.
			board_mapper.plusView(board_id);
			if(viewCookie == null) {
				// 본적도 없고 쿠키도 없는 경우
				viewCookie = new Cookie("viewed", board_id.toString());
			} else {
				// 쿠키는 있지만 이 글은 처음보는 경우 (맨 뒤에 새 /id를 추가)
				viewCookie.setValue(viewCookie.getValue() + "/" + board_id);
			}
			viewCookie.setPath(req.getContextPath() + "/service/board");
			resp.addCookie(viewCookie);
		}
		return board_mapper.get(board_id);
	}
}
