package com.java.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;
import com.java.dto.LoanDto;
import com.java.service.BoardService;
import com.java.service.EmailService;
import com.java.service.LoanService;
@Controller
public class FController {
	
	@Autowired BoardService boardService;
	@Autowired LoanService loanService;
	@Autowired EmailService emailService; 
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/email")
	public String email() {
		return "email";
	}
	@PostMapping("/email_send")
	@ResponseBody
	public String email_send(String name, String email) {
		System.out.println("controller name : "+name);
		System.out.println("controller email : "+email);
		
		//이메일 전송
//		emailService.email_send(name, email);
		
		//이메일전송2 (html 형식으로 보내기)
		emailService.email_send2(name, email);
		
		
		
		return "1";
	}
	
	@GetMapping("/notice_list")
	public ModelAndView notice_list() {
		//board 테이블의 데이터를 가져와서 출력하시오
		
		ArrayList<BoardDto> list = boardService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("notice_list");
		
		return mv;
	}
	
	@GetMapping("/chart") //차트그리기
	public String chart() {
		return "chart";
	}
	
	@PostMapping("/chart_data") //ajax chart 데이터 전송
	@ResponseBody
	public ArrayList<LoanDto> chart_data(String data) {
		System.out.println("controller data: "+data);
		
		ArrayList<LoanDto> list = new ArrayList<LoanDto>();
		//Loan리스트 지역별 대출액 합계
		if (data.equals("region")) list = loanService.chartDraw(data);
		//Loan리스트 기간별 대출액 합계
		else list = loanService.chartDraw2(data);
			
			 
		System.out.println("list 개수 : "+list.size());
		
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getPeriod()+","+list.get(i).getRegion()+","+list.get(i).getAmt());
		}//for
		
		return list;
		
	}
}
