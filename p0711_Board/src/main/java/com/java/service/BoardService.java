package com.java.service;

import java.util.ArrayList;

import com.java.dto.BoardDto;

public interface BoardService {

	//게시판리스트 가져오기
	ArrayList<BoardDto> selectList();

	//게시글 하나 불러오기
	BoardDto selectOne(BoardDto bdto);

	//게시글 작성하고 저장하기
	void insertBoard(BoardDto bdto);

	//게시글 삭제
	void deleteBoard(BoardDto bdto);

	//게시글 수정
	BoardDto updateBoard(BoardDto bdto);

	//수정페이지 저장
	void doUpdateBoard(BoardDto bdto);

	//답글 페이지 저장
	void insertReply(BoardDto bdto);

	

}
