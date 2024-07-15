package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emp_DepartDto {

	private int employee_id;
	private String emp_name;
	private Timestamp hire_date;
	private int department_id;
	private int parent_id;
	private String department_name;
	private int salary;
}
