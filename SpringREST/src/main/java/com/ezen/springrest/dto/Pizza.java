package com.ezen.springrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pizza {

	private String name;
	private Integer price;
	private Double calories;
}
