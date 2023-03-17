package sai.pork.springboard.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import lombok.Data;

@Data
public class Comment {

	Integer comment_id;
	Integer board_id;
	String comment_writer;
	String comment_pw;
	String comment_content;
	String comment_write_date;
	
//	private static SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getCreationDateTime() {
		Calendar today = Calendar.getInstance();
		String formatted_today = dayFormat.format(today.getTime());
		String write_date_day = comment_write_date.substring(0,10);
		String write_date_time = comment_write_date.substring(11,16);
		
		return write_date_day.equals(formatted_today) ?
				write_date_time : write_date_day;
	}
}
