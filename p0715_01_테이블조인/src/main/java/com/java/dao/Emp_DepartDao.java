package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Emp_DepartDto;

@Mapper
public interface Emp_DepartDao {

	ArrayList<Emp_DepartDto> selectAll();

}
