package com.vinan.reactproject.model.dto;

import lombok.Data;

@Data
public class UserDto {
	private Integer id;

	private String email;
	private String phone;
	private String password;
}
