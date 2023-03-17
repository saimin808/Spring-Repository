package sai.pork.springboard.dto;

public class Pagination {
	
	private int page;
	private int size;
	private int board_size;
	
	public Pagination(int page, int size, int board_size) {
		this.page = page;
		this.size = size;
		this.board_size = board_size;
	}
	
	public int getStartIndex() {
		return (page - 1) * size;
	}
	
	public int getEndIndex() {
		int end_index = page * size;
		return end_index > board_size ? board_size : end_index;
	}
	
	public int getPaginationStart() {
		return (page / size) * size + 1;
	}
	
	public int getPaginationEnd() {
		int max_page = board_size % size == 0 ?
				board_size / size : board_size / size + 1;
		int pagination_end = (page / size + 1) * size;
		return pagination_end > max_page ? max_page : pagination_end;	
	}
}
