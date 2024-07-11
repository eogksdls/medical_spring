package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/board")
public class BoardController {
	
//	BoardServiceImpl boardServiceImpl = new BoardServiceImpl(); //객체선언
//	BoardService boardService = new BoardService();  //다형성
	
	@Autowired
	BoardService boardService;  //IOC 컨테이너에서 주입받음. -> 유지보수 시 편리함 쓰고 싶은 객체에 가서 @Service 선언해주면 됨
	
	@RequestMapping("/list") //게시글 리스트 불러오기
	public ModelAndView list() { 
		
		ArrayList<BoardDto> list = boardService.selectList();
		
		//mv
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@RequestMapping("/view") //뷰페이지
	public ModelAndView view(BoardDto bdto) { //객체로 받기 bdto엔 bno값과 같은 데이터들이 들어있다
		
		//1개
		BoardDto boardDto = boardService.selectOne(bdto);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardDto",boardDto);
		mv.setViewName("board/view");
		
		return mv;
	}
	
	@GetMapping("/write") //글쓰기 화면
	public String write() {
		
		return "board/write";
	}
	
	@PostMapping("/write") //글쓰기 저장(오버로딩)
	public String write(BoardDto bdto, @RequestPart MultipartFile files) { 
		//id,btitle,bcontent,files
		System.out.println("controller files : "+files.getOriginalFilename());
		String uFile="";
		//파일이 존재할 시
		if (!files.isEmpty()) {
			//jsp
			long time = System.currentTimeMillis();
//			System.out.println("time : "+time);
			
			//uuid 방식 :좀 길다는 단점...
//			UUID uuid = UUID.randomUUID();
//			System.out.println("uuid : "+uuid);
			
			uFile = String.format("%d_%s", time,files.getOriginalFilename());
			String saveUrl = "c:/upload/";
			File f = new File(saveUrl+uFile);
			try {
				files.transferTo(f);
			} catch (Exception e) { e.printStackTrace(); } //파일업로드 
		}//if
		
		//변경된 파일이름 저장
		bdto.setBfile(uFile);
		
		boardService.insertBoard(bdto);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete")
	public String delete(BoardDto bdto) { //게시글 삭제하기
		System.out.println("controller delete bno : "+bdto.getBno());
		boardService.deleteBoard(bdto);
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/update") //게시글 수정
	public ModelAndView update(BoardDto bdto) { 
		
		BoardDto boardDto = boardService.updateBoard(bdto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardDto",boardDto);
		mv.setViewName("/board/modify");
		
		return mv;
	}
	
	@RequestMapping("/doUpdate") //게시글 수정후 저장
	public String doUpdate(BoardDto bdto, @RequestPart MultipartFile files) {
		//수정페이지 저장 bno,id,btitle,bcontent,bfile
		
		String uFile="";
		//파일이 존재할 시
		if (!files.isEmpty()) {
			//jsp
			long time = System.currentTimeMillis();
			
			uFile = String.format("%d_%s", time,files.getOriginalFilename());
			String saveUrl = "c:/upload/";
			File f = new File(saveUrl+uFile);
			try {
				files.transferTo(f);
			} catch (Exception e) { e.printStackTrace(); } //파일업로드 
			
			//변경된 파일이름 저장
			//기존 게시글에 파일이 있었을 때만 파일 이름을 저장하기 위해서 if문 안에 넣어준다
			bdto.setBfile(uFile);  
		}//if
		
		
		boardService.doUpdateBoard(bdto);
		
		return "redirect:/board/view?bno="+bdto.getBno();
	}
	
	@RequestMapping("/reply")  //답글 페이지 작성
	public ModelAndView reply(BoardDto bdto) {
		
		BoardDto boardDto = boardService.selectOne(bdto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardDto",boardDto);
		mv.setViewName("board/reply");
		
		return mv;
	}
	
	@RequestMapping("/doReply") //답글페이지 저장
	public String doReply(BoardDto bdto,@RequestPart MultipartFile files ) { //bdto엔 bno값과 같은 데이터들이 들어있다
		
		String uFile="";
		//파일이 존재할 시
		if (!files.isEmpty()) {
			long time = System.currentTimeMillis();
			
			uFile = String.format("%d_%s", time,files.getOriginalFilename());
			String saveUrl = "c:/upload/";
			File f = new File(saveUrl+uFile);
			try {
				files.transferTo(f);
			} catch (Exception e) { e.printStackTrace(); } //파일업로드 
		}//if
		
		//변경된 파일이름 저장
		bdto.setBfile(uFile);
		
		//저장
		boardService.insertReply(bdto);
		
		return "redirect:/board/list";
	}
}
