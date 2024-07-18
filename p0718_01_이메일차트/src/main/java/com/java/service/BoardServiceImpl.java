package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardDao boardDao;
	
	@Override
	public ArrayList<BoardDto> selectAll() {
		
		ArrayList<BoardDto> list = boardDao.selectAll();
				
		return list;
	}

}
