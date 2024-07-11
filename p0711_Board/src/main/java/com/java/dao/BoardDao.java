package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper //mybatis와 Dao 연결
public interface BoardDao {

	//게시판리스트 가져오기
	ArrayList<BoardDto> selectList();

	//게시글 1개 가져오기
	BoardDto selectOne(BoardDto bdto);
	
	//클릭시 조회수 1 증가
	void updateBhit(BoardDto bdto);

	//게시글 작성하고 저장하기
	void insertBoard(BoardDto bdto);
	
	//게시글 삭제하기
	void deleteBoard(BoardDto bdto);

	//게시글 수정 저장하기
	void updateBoard(BoardDto bdto);

	//답글페이지 저장
	void insertReply(BoardDto bdto);

	//bstep 1씩 증가 -> 가장 최신 답글을 위로 올리기 위해
	void updateBstep(BoardDto bdto);

	


	

}
