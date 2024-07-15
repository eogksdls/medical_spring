package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.dto.EmpDto;

@Mapper
public interface EmpDao {

	ArrayList<EmpDto> selectAll();

}
