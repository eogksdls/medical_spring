package com.java.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.java.dao.NoticeDao;
import com.java.dto.CommentDto;
import com.java.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired NoticeDao noticeDao;
	
	@Override  //notice 리스트
	public ArrayList<NoticeDto> selectAll() {

		ArrayList<NoticeDto> list = noticeDao.selectAll(); 
		return list;
	}

	@Override  //notice 1개 가져오기
	public Map<String, Object> selectOne(NoticeDto noticeDto) {

		Map<String, Object> map = new HashMap<>();
		
		// 조회수 1증가
		noticeDao.updateBhit(noticeDto);
		//게시글 1개 가져오기
		NoticeDto nDto = noticeDao.selectOne(noticeDto);
		//이후 페이지 추가 이전글 다음글
		
		//게시글 하단댓글 모두 가져오기 - 클릭하는 게시글의 댓글
		ArrayList<CommentDto> list = noticeDao.selectAllCommentB(noticeDto);
		//댓글 개수 세기
		int countComment = noticeDao.countAllCommentB(noticeDto);
		
		map.put("nDto", nDto);
		map.put("list", list);
		map.put("countComment", countComment);
		
		return map;
	}

	@Override   //하단댓글저장, 1개 가져오기
	public CommentDto insertCommentB(CommentDto commentDto) {
		System.out.println("service cno"+commentDto.getCno());
		// 하단댓글 저장
		noticeDao.insertCommentB(commentDto);
		// cno 생성 후 출력
		System.out.println("service cno2"+commentDto.getCno());
		// 하단댓글 1개 가져오기
		CommentDto cDto = noticeDao.selectOneCommentB(commentDto);
		return cDto;
	}

	@Override  //하단댓글삭제
	public void deleteCommentB(CommentDto commentDto) {
		System.out.println("service cno"+commentDto.getCno());
		// 하단댓글 삭제
		noticeDao.deleteCommentB(commentDto);
		return;
		
	}

	@Override  //하단댓글수정
	public CommentDto updateCommentB(CommentDto commentDto) {
		System.out.println("service cno : "+commentDto.getCno());
		//하단댓글 수정
		noticeDao.updateCommentB(commentDto);
		//하단댓글 1개 가져오기
		CommentDto cDto = noticeDao.selectOneCommentB(commentDto);
		
		return cDto;
	}


}
