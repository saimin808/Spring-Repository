package sai.pork.springboard.mapper;

import java.util.List;

import sai.pork.springboard.dto.Comment;

public interface CommentXmlMapper {

	// 한 게시물의 모든 댓글들 불러오기
	List<Comment> getComments(Integer board_id);
	
	// 댓글 하나 불러오기
	Comment getComment(Integer comment_id);
	
	// 댓글 추가
	Integer writeComment(Comment comment);
	
	// 댓글 수정
	Integer modifyComment(Comment comment);
	
	// 댓글 삭제
	Integer deleteComment(Integer comment_id);
	
	// 한 게시물의 댓글들 삭제
	Integer deleteComments(Integer board_id);
	
}
