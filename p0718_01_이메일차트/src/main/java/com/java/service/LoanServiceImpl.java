package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.LoanDao;
import com.java.dto.LoanDto;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired LoanDao loanDao;
	
	@Override  //지역별
	public ArrayList<LoanDto> chartDraw(String data) {
		
		ArrayList<LoanDto> list = loanDao.chartDraw();
		
		return list;
	}

	@Override  //기간별
	public ArrayList<LoanDto> chartDraw2(String data) {
		
		ArrayList<LoanDto> list = loanDao.chartDraw2();
		
		return list;
	}

}
