package com.ezen.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Employee;

@Controller
public class SampleController {

	private static Logger log = LogManager.getLogger(SampleController.class);
	
	@RequestMapping(value = {"/sample/", "/sample"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		
		// /WEB-INF/views/ 아래의 경로를 리턴해야 한다.
		return "sample/index";
	}
	
	@GetMapping(value = {"/sample/get"})
	public String get() {
		return "sample/form";
	}
	
	@PostMapping(value = {"/sample/post"})
	public String post(HttpServletRequest request) {
		log.info(request.getParameter("employee_id"));
		log.info(request.getParameter("first_name"));
		log.info(request.getParameter("last_name"));
		
//		Employee e = new Employee();
//		
//		e.setEmployee_id(Integer.parseInt(request.getParameter("employee_id")));
//		
//		request.setAttribute("employee", e);
		
		return "sample/result";
	}
	
	@PostMapping("/sample/post2")
	public String post2(Employee emp) {
		log.info(emp);
		
		System.out.println(emp);
		return "sample/result";
	}
	
	
	@PostMapping("/sample/post3")
	public String post3(
			@ModelAttribute("employee_id")
			Integer employee_id,
			@ModelAttribute("first_name")
			String first_name,
			@ModelAttribute("last_name")
			String last_name) {
		
		log.info(employee_id);
		log.info(first_name);
		log.info(last_name);
		
		return "sample/result";
	}
	
	@GetMapping("/sample/find/void")
	public void orange(Employee e) {
		log.info(e);
	}
	
}
