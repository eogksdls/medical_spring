package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.EmpDao;
import com.java.dto.EmpDto;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao empDao;
	
	@Override
	public ArrayList<EmpDto> selectAll() {
		ArrayList<EmpDto> list = empDao.selectAll();
		return list;
	}

	
}
