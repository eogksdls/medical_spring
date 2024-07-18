package com.java.service;

import java.util.ArrayList;

import com.java.dto.LoanDto;

public interface LoanService {

	//지역별
	ArrayList<LoanDto> chartDraw(String data);
	//기간별
	ArrayList<LoanDto> chartDraw2(String data);

}
