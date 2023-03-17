package com.ezen.springdb.board.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springdb.board.dto.BoardDTO;
import com.ezen.springdb.board.mapper.ServiceBoardMapper;
import com.ezen.springdb.board.service.BoardListService;

public class BoardListServiceImpl implements BoardListService {

	@Autowired
	ServiceBoardMapper board_mapper;
	
	@Override
	public BoardDTO clickTitle(Integer board_id, HttpServletRequest req, HttpServletResponse resp) {
		
		Cookie[] cookies = req.getCookies();
		
		boolean viewed = false;
		
		JSONObject obj = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName().equals("viewed")) {

					// 쿠키에서 값을 꺼내면 {"ids":[1,2,3,4]} 이렇게 생긴 JSONString이 나온다.
					String jsonString = cookie.getValue();

					// 문자열을 JSONObject 타입으로 파싱하기 위한 객체
					JSONParser parser = new JSONParser();

					try {
						// 파싱을 실행
						obj = (JSONObject) (parser.parse(jsonString));

						// 값으로 꺼낸것이 자바스크립트 배열이기 때문에 JSONArray로 받음
						JSONArray ids = (JSONArray) obj.get("ids");

						// 조회가 된다는 것은 본적이 있다는 뜻
						viewed = ids.contains(board_id);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(!viewed) {
			// 해당 글의 조회수를 1 올린다. (영속 계층, Mapper)
			board_mapper.plusView(board_id);
			
			// 조회한 적도 없고 쿠키도 없는 경우
			if (obj == null) {
				
				// 자바 스크립트 오브젝트(Map타입)처럼 사용할 수 있다.
				
				// const json = { ids: [board_id] }
				JSONObject json = new JSONObject(); // JSON은 자바스크립트의 {}
				JSONArray arr = new JSONArray();
				
				arr.add(board_id);
				json.put("ids", arr);
				
				Cookie viewCookie = new Cookie("viewed", json.toJSONString());
				
				viewCookie.setPath("/service/board");
				
				resp.addCookie(viewCookie);
			} else {
				// 처음 조회하지만 쿠키는 있는 경우
				JSONArray ids = (JSONArray) obj.get("ids");
				ids.add(board_id);
				obj.put("ids", ids);
				
				Cookie viewCookie = new Cookie("viewed", obj.toJSONString());
				viewCookie.setPath("/service/board");
				
				resp.addCookie(new Cookie("viewed", obj.toJSONString()));
			}
		}
		
		// 글의 상세 내용을 가져온다. (영속 계층, Mapper)
		return board_mapper.get(board_id);
	}
}
