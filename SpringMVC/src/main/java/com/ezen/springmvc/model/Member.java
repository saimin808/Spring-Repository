package com.ezen.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor  // 모든 객체를 포함한 생성자
@NoArgsConstructor	// 아무것도없는 기본 생성자
@RequiredArgsConstructor // NonNull 붙은 것만 포함한 생성자
public class Member {

	@NonNull
	private String mem_id;
	@NonNull
	private String mem_name;
	@NonNull
	private String mem_password;
	@NonNull
	private String cellphone;
	
	private String telephone;
	private String introduce;
}
