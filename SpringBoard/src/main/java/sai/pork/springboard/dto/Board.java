package sai.pork.springboard.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.Data;

@Data
public class Board {

	private Integer board_id;
	private String writer;
	private String write_pw;
	private String write_type;
	private String write_title;
	private String write_content;
	private String write_date;
	private Integer write_view;
	private Integer write_recommand;
	private	Integer write_not_recommand;
	
//	private static SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
	private static SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd");
	
	// Getter를 만들어 놓으면 JSP의 EL에서는 필드가 있는 것처럼 사용할 수 있다.
//	public String getCreationDateTime() {
//		
//		// DB에 있는 sysdate를 날짜 + 시간 까지 가져오려면 Date 타입이 아닌 Timestamp타입으로 받아와야 함
//		// 그 Timestamp타입은 LocalDateTime으로 변환 가능한데
//		// LocalDateTime으로 구한 오늘 날짜+시간과 DB에서 받아온 LocalDateTime으로 변환한 DB에서 받아온
////			오늘 날짜를 비교해준다
//		LocalDateTime today = LocalDateTime.now();
//		LocalDateTime creationDate = write_date.toLocalDateTime();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//		
//		return formatter.format(creationDate).equals(formatter.format(today)) ?
//				timeFormat.format(write_date) : dayFormat.format(write_date);
//	}
	
//	public String getToday() {
//		LocalDateTime today = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:kk:mm:ss");
//		
//		return formatter.format(today);
//	}
	
	// write_date를 String 타입으로 넣어줄때 (simpledateformat을 이용)
	public String getCreationDateTime() {
		Calendar today = Calendar.getInstance();
		String formatted_today = dayformat.format(today.getTime());
		String write_date_day = write_date.substring(0,10);
		String write_date_time = write_date.substring(11,16);
		
		return write_date_day.equals(formatted_today) ?
				write_date_time : write_date_day;
	}
	
}
