package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.LoanDto;

@Mapper
public interface LoanDao {

	//지역별
	ArrayList<LoanDto> chartDraw();
	//기간별
	ArrayList<LoanDto> chartDraw2();

}
