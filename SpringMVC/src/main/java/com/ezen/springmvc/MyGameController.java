package com.ezen.springmvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/mygame")
@Controller
public class MyGameController {

	@GetMapping(value = {"/main_T/", "/main_T"})
	public String gamePage() {
		
		return "game/game_T";
	}
	
	@GetMapping("/index")
	public void index() {
		
	}
	
	@PostMapping("/index")
	public String index2(String user, HttpServletResponse resp) {
		// ��Ű�� response�� �Ǿ� ������ ��
		
		if (user != null && user != "") {
			Cookie cookie = new Cookie("user", user);
			
			cookie.setMaxAge(604800); //������
			
			resp.addCookie(cookie);
		}
		
		// ��Ʈ�ѷ����� �����̷�Ʈ �ϴ� ��� (Context Path �˾Ƽ� �ٿ���)
		return "redirect:/mygame/main_T";
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/mygame/main_T";
	}
}
