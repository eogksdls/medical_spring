package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public ArrayList<BoardDto> selectList() {
		
		//mybatis  연결해서 list 가져오기
		ArrayList<BoardDto> list = boardDao.selectList();
		
		return list;
	}

	@Override
	public BoardDto selectOne(BoardDto bdto) {
		boardDao.updateBhit(bdto); //클릭시 조회수 1 증가 //근데 F5번만 눌러도 조회수가 올라가네
		BoardDto boardDto = boardDao.selectOne(bdto); //게시글 보여지기
		
		return boardDto;
	}

	@Override
	public void insertBoard(BoardDto bdto) {
		boardDao.insertBoard(bdto);
	}

	@Override
	public void deleteBoard(BoardDto bdto) {
		boardDao.deleteBoard(bdto);
	}

	@Override
	public BoardDto updateBoard(BoardDto bdto) {
		BoardDto boardDto = boardDao.selectOne(bdto); //게시글 1개 가져오기
		
		return boardDto;
	}

	@Override
	public void doUpdateBoard(BoardDto bdto) {
		boardDao.updateBoard(bdto); //게시글 수정 저장
	}

	@Override
	public void insertReply(BoardDto bdto) {
		//답글 달때마다 부모 게시글보다 큰 bstep을 가진 답글들에게 1씩 증가시킴
		boardDao.updateBstep(bdto); 
		boardDao.insertReply(bdto); //답글 저장
		
	}

}
