package com.ezen.springmvc.model;

import lombok.Getter;
import lombok.Setter;

public class Department {

	@Getter
	private Integer department_id;
	
	@Setter
	@Getter
	private String department_name;
}
