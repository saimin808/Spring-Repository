package com.ezen.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor  // ��� ��ü�� ������ ������
@NoArgsConstructor	// �ƹ��͵����� �⺻ ������
@RequiredArgsConstructor // NonNull ���� �͸� ������ ������
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
