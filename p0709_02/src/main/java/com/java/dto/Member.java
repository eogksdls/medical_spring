package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //전체생성자
@NoArgsConstructor  //기본생성자
@Data  //getter and setter 생성
public class Member {

	private String id;
	private String pw;
	private String name;
	private String phone;
	private String gender;
	private String hobby;
	private String[] hobbys;
	
}
