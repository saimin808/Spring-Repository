package com.ezen.springmvc;

import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springmvc.model.Player;

@RequestMapping("/game")
@Controller
public class GameController {
	
	Map<String, Player> players;
	
	@GetMapping(value = {"/main/", "/main"})
	public String mainPage() {
		
		return "game/index";
	}
	
	@GetMapping(value = {"/game_set/", "/game_set"})
	public String gameSet(HttpServletRequest req, @ModelAttribute("player")
								String player, HttpServletResponse resp) {
		
		Cookie cookie = new Cookie("player", player);
		cookie.setMaxAge(-1);
		Cookie[] cookies = req.getCookies();
		
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i] == null || cookies[i].getValue() == null) {
				resp.addCookie(cookie);
			}
		}
		
		return "game/index";
	}
	
	/*
	 	가위 1 / 바위 2 / 보 3
	 	1. 승리 경우의 수
			- 가위1 - 보3 : 바위2 - 가위1 : 보3 - 바위 2
		2. 패배 경우의 수
			- 가위1 - 바위2 : 바위2 - 보3 : 보3 - 가위1
	*/
	@PostMapping(value = {"/game_result/","/game_result"})
	public String gameResult(HttpServletRequest req,
									@ModelAttribute("player")
									String player) {
		
		Integer com_pick = (int)(Math.random()*2+1);
		Integer player_pick = Integer.parseInt(req.getParameter("answer"));
		Integer result = player_pick - com_pick;
		
		
		
//		if(result == 1 || result == -2) {
//			req.setAttribute("result", "win");
//			record.setGameCnt(record.getGameCnt()+1);
//			record.setWin(record.getWin()+1);
//		} else if (result == -1 || result == 2) {
//			req.setAttribute("result", "lose");	
//			record.setGameCnt(record.getGameCnt()+1);
//			record.setLose(record.getLose()+1);
//		} else if (result == 0) {
//			req.setAttribute("result", "draw");
//			record.setGameCnt(record.getGameCnt()+1);
//			record.setDraw(record.getDraw()+1);
//		}
		
		return "game/index";
	}
	
	@GetMapping(value = {"/logout/","/logout"})
	public String logout(HttpServletRequest req,
								@ModelAttribute("player")
								String player) {
		ServletContext application = req.getSession().getServletContext();
		
		application.setAttribute("player", "logout");
		
		return "game/index";
	}
	
}
