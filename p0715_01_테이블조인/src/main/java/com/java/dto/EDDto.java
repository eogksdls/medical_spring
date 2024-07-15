package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EDDto {
	
	//있는걸 그대로 사용하기
	private EmpDto empDto;
	private DepartDto departDto;
}
