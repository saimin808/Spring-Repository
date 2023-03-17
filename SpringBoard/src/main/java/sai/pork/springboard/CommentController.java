package sai.pork.springboard;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import sai.pork.springboard.dto.Board;
import sai.pork.springboard.dto.Comment;
import sai.pork.springboard.mapper.BoardXmlMapper;
import sai.pork.springboard.mapper.CommentXmlMapper;

@Log4j2
@RequestMapping("/comment")
@Controller
public class CommentController {

	@Autowired
	DataSource ds;
	
	@Autowired
	BoardXmlMapper board_mapper;
	
	@Autowired
	CommentXmlMapper comment_mapper;
	
	@PostMapping("/write/do")
	public String writeComment(Model model, Comment comment, Integer board_id) {
		Integer row = comment_mapper.writeComment(comment);
		
		if(row > 0) {
			
			model.addAttribute("status", "comment_complete");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		} else {
			
			model.addAttribute("status", "comment_failed");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		}	
	}
	
	@GetMapping("/modify_pw_check")
	public String passwordCheckBeforeModify(Model model, Integer comment_id) {
		
		model.addAttribute("status","modify");
		model.addAttribute("comment_id", comment_id);
		
		return "comment/comment_pw_check";
	}
	
	@PostMapping("/modify")
	public String modifyCommentPage(Model model, Integer comment_id, String comment_pw) {
		Comment comment = comment_mapper.getComment(comment_id);
		
		if(comment_pw.equals(comment.getComment_pw())) {
			model.addAttribute("comment", comment);
			
			return "comment/comment_modify";
		} else {
			model.addAttribute("status", "modify");
			model.addAttribute("pw_check", "wrong_password");
			
			return "redirect:/comment/comment_pw_check";
		}
		
		
	}
	
	@PostMapping("/modify/do")
	public String modifyComment(Model model, Comment comment) {
		
		Integer row = comment_mapper.modifyComment(comment);
		
		if(row > 0) {
			
			model.addAttribute("status", "modify_complete");
			model.addAttribute("board_id", comment.getBoard_id());
			
			return "redirect:/board/show";
		} else {
			
			model.addAttribute("status", "modify_failed");
			model.addAttribute("board_id", comment.getBoard_id());
			
			return "redirect:/board/show";
		}	
	}
	
	@GetMapping("/delete_pw_check")
	public String passwordCheckBeforeDelete(Model model, Integer comment_id, Integer board_id) {
		
		model.addAttribute("comment_id", comment_id);
		model.addAttribute("board_id", board_id);
		model.addAttribute("status", "delete");
		
		return "comment/comment_pw_check";
	}
	
	@PostMapping("/delete/do")
	public String deleteComment(Model model, Integer comment_id, Integer board_id) {
		Integer row = comment_mapper.deleteComment(comment_id);
		
		if(row > 0) {
			model.addAttribute("status", "delete_complete");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		} else {
			model.addAttribute("status", "delete_failed");
			model.addAttribute("board_id", board_id);
			
			return "redirect:/board/show";
		}
	}
	
}
