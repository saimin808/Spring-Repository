package sai.pork.springboard;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
import sai.pork.springboard.dto.Board;
import sai.pork.springboard.dto.Comment;
import sai.pork.springboard.mapper.BoardXmlMapper;
import sai.pork.springboard.mapper.CommentXmlMapper;

@Log4j2
@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	DataSource ds;
	
	@Autowired
	BoardXmlMapper board_mapper;
	
	@Autowired
	CommentXmlMapper comment_mapper;
	
	@GetMapping(value = {"/index", "/"})
	public String index() {	
		return "board/index";
	}
	
	@GetMapping("/list")
	public String getBoardList(Model model, @RequestParam(value="page", required=false)String pageStr) {
		
		List<Board> boards = board_mapper.getBoards();
		
		//현재 페이지 값을 통해 가져온 전체 글들 중 10개만 어트리뷰트에 실을 수 있도록
		// 부분 리스트를 생성해야 한다.
		int page;
		
		if (pageStr == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageStr);
		}
		
		// start_index : (page - 1) * 10
		// end_index : page * 10 - 1 or 글의 최대 개수
		int page_size = 10;
		int board_size  = boards.size();
		int start_index = (page - 1) * page_size + 1;
		int end_index = page * page_size;
		end_index = end_index > board_size ? board_size : end_index;
		
		// 전체 글이 47개면 5페이지 필요하다
		int max_page = board_size % page_size == 0 ?
			board_size / page_size : board_size / page_size + 1;
				
		// 현재 페이지가 7일때 1 ~ 10로 나왔으면 함
		int pagination_start = (page / page_size) * page_size + 1;
		int pagination_end = (page / page_size + 1) * page_size;
		pagination_end = pagination_end > max_page ? max_page : pagination_end;
				
		System.out.printf("현재 페이지는 %d이고, 페이지 네비게이션 시작 숫자는 %d, 마지막 숫자는 %d입니다.,",
						page, pagination_start, pagination_end);
				
		model.addAttribute("boards", boards);
		model.addAttribute("pagination_start", pagination_start);
		model.addAttribute("pagination_end", pagination_end);
		
		return "board/list";
	}
	
	
	@GetMapping("/write")
	public String writeBoardPage() {
		
		return "board/write";
	}
	
	@PostMapping("/write/do")
	public String writeBoard(Model model, Board board) {
		
		int row = board_mapper.writeBoard(board);
		
		model.addAttribute("status", "write_complete");
		
		log.info("write result : " + row);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/show")
	public String getBoard(Model model, Integer board_id) {
		Board board = board_mapper.getBoard(board_id);
		List<Comment> comments = comment_mapper.getComments(board_id);
		Integer row = board_mapper.updateView(board_id);
		
		if(row > 0) {
			model.addAttribute("board", board);
			model.addAttribute("comments", comments);
			model.addAttribute("comments_size", comments.size());
			return "board/show";
		} else {
			model.addAttribute("status", "view_error");
			
			return "redirect:/board/list";
		}
		
	}
	
	@GetMapping("/modify_pw_check")
	public String passwordCheckBeforeModify(Model model, Integer board_id) {

		model.addAttribute("status","modify");
		model.addAttribute("board_id", board_id);
		
		return "board/pw_check";
	}
	
	@PostMapping("/modify")
	public String modifyBoardPage(Model model, Integer board_id, String write_pw) {
		Board board = board_mapper.getBoard(board_id);
		
		if(write_pw.equals(board.getWrite_pw())) {
			
			model.addAttribute("board", board);
						
			return "board/modify";
		} else {
			
			model.addAttribute("status", "wrong_password");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/modify_pw_check";
		}
	}
	
	@PostMapping("/modify/do")
	public String modifyBoard(Model model, Board board) {
		
		int row = board_mapper.modifyBoard(board);
		
		log.info("modify result : " + row);
		
		if(row > 0) {
			model.addAttribute("status", "modify_complete");
		
			return "redirect:/board/list";
		} else {
			model.addAttribute("status", "modify_failed");
			
			return "redirect:/board/list";
		}
	}
	
	@GetMapping("/delete_pw_check")
	public String passwordCheckBeforeDelete(Model model, Integer board_id) {
		
		model.addAttribute("status", "delete");
		model.addAttribute("board_id", board_id);
		
		return "board/pw_check";
	}
	
	@PostMapping("/delete/do")
	public String deleteBoardPage(Model model, Integer board_id, String write_pw) {
		Board board = board_mapper.getBoard(board_id);
		
		if(write_pw.equals(board.getWrite_pw())) {
			List<Comment> comments = comment_mapper.getComments(board_id);
			Integer row = null;
			if(comments.size() > 0) { 
				row = comment_mapper.deleteComments(board_id);
			}
			
			if(row == null || row > 0) {
				Integer row2 = board_mapper.deleteBoard(board_id);
				
				if(row2 > 0) {
					model.addAttribute("status", "delete_complete");
							
					return "redirect:/board/list";
				} else {
					model.addAttribute("status", "delete_failed");
					
					return "redirect:/board/list";
				}
			} else {
				
				model.addAttribute("status", "delete_failed");
				
				return "redirect:/board/list";
			}
		} else {
			
			model.addAttribute("status", "delete");
			model.addAttribute("pw_check", "wrong_password");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/pw_check";
		}
	}
	
	@GetMapping("/recommend")
	public String recommendBoard(Model model, @RequestParam("board_id") Integer board_id) {
		System.out.println(board_id);
		
		Integer row = board_mapper.recommendBoard(board_id);
		
		if(row > 0) {
			model.addAttribute("status", "recommend_complete");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		} else {
			model.addAttribute("status", "recommend_failed");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		}		
	}
	
	@GetMapping("/not_recommend")
	public String notRecommendBoard(Model model, @RequestParam("board_id") Integer board_id) {
		System.out.println(board_id);
		
		Integer row = board_mapper.notRecommendBoard(board_id);
		
		if(row > 0) {
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		} else {
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		}		
	}
}
