package com.java.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.CommentDto;
import com.java.dto.NoticeDto;

@Mapper
public interface NoticeDao {

	//게시글 리스트 가져오기
	ArrayList<NoticeDto> selectAll();

	//게시글 1개 가져오기
	NoticeDto selectOne(NoticeDto noticeDto);
	//조회수 1증가
	void updateBhit(NoticeDto noticeDto);
	// 해당 게시글 댓글 보여지기
	ArrayList<CommentDto> selectAllCommentB(NoticeDto noticeDto);
	//댓긓 개수
	int countAllCommentB(NoticeDto noticeDto);

}
