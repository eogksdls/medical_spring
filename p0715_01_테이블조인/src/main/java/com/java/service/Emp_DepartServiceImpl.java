package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.Emp_DepartDao;
import com.java.dto.Emp_DepartDto;

@Service
public class Emp_DepartServiceImpl implements Emp_DepartService {

	@Autowired
	Emp_DepartDao emp_departDao;
	
	@Override
	public ArrayList<Emp_DepartDto> selectAll() {

		ArrayList<Emp_DepartDto> list = emp_departDao.selectAll();
		
		return list;
	}

}
