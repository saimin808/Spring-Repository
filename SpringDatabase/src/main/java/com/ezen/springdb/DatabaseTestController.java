package com.ezen.springdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springdb.board.dto.BoardDTO;
import com.ezen.springdb.dto.Employee;
import com.ezen.springdb.mapper.BoardMapper;
import com.ezen.springdb.mapper.EmployeeMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/test")
@Controller
public class DatabaseTestController {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	EmployeeMapper mapper;
	
	@Autowired
	BoardMapper board_mapper;
	
	@GetMapping(value = {"/index", "/"})
	public String index() {	
		return "test/index";
	}
	
	@GetMapping(value= "/employees")
	public void employees(Model model) {
		String query = "SELECT * FROM employees";
		try (Connection conn = ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery();) {
			
			List<Employee> employees = new ArrayList<>();
			
//			while (rs.next()) {
//				employees.add(new Employee(
//						rs.getInt("employee_id"),
//						rs.getString("first_name"),
//						rs.getString("last_name"),
//						rs.getInt("department_id"),
//						rs.getString("job_id"),
//						rs.getInt("salary")
//					));
//			}
			
			// 모델은 attribute를 add하는 곳
			model.addAttribute("employees", employees);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Mybatis로 여러 줄 SELECT (알아서 List<DTO>타입으로 변환해서 가져옴)
	@GetMapping("/employees2")
	public String employees2(Model model) {
		List<Employee> employees = mapper.getList();
		
		log.info(employees);
		
		model.addAttribute("employees", employees);
		
		return "test/employees";
	}
	
	// Mybatis로 하나만 SELECT (알아서 DTO타입으로 가져옴)
	@GetMapping("/board/get")
	public String getBoard(Model model, Integer board_id) {
		model.addAttribute("board",board_mapper.get(board_id));
		
		return "test/board_detail";
	}
	
	@GetMapping("/board/add")
	public String addBoard(Model model, BoardDTO board) {
		int row = board_mapper.add(board);
		
		return "redirect:/test/";
	}
	
	@GetMapping("/board/update")
	public String updateBoard(BoardDTO board) {
		int row = board_mapper.update(board);
		
		log.info("update : "+ row);
		
		return "redirect:/test/board_list";
	}
	
	@GetMapping("/board/delete")
	public String deleteBoard(Integer board_id) {
		int row = board_mapper.delete(board_id);
		
		log.info(row);
		
		return "redirect:/test/board_list";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		model.addAttribute("boards",board_mapper.getAll());
		
		return "test/board_list";
	}
	
}
