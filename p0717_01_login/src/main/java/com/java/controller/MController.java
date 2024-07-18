package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.MemberDto;
import com.java.sevice.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
public class MController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	
	@GetMapping("/member/login")   //로그인 페이지 열기
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/login")   //로그인 확인
	public ModelAndView login(MemberDto memberDto) {
		System.out.println("controller id : "+memberDto.getId());
		System.out.println("controller pw : "+memberDto.getPw());
		
		//db 확인
		int chkLogin = 0;  //로그인실패
		MemberDto mDto = memberService.selectLogin(memberDto);
		
		if(mDto!=null) { //session추가 : 작성자에 아이디가 자동으로 들어감
			chkLogin = 1; //로그인 성공
			System.out.println("controller name : "+mDto.getName());
			session.setAttribute("sessionId", mDto.getId());  //세션부여 id
			session.setAttribute("sessionName", mDto.getName());  //세션부여 pw -> 로그인 정보 저장
		}
		System.out.println("controller chkLogin : "+chkLogin);
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("chkLogin",chkLogin);
		mv.setViewName("member/doLogin");
		
		return mv;
	}
	
	@RequestMapping("/member/logout")
	public ModelAndView logout() {
		int chkLogin = 3;  //로그아웃
		
		session.invalidate();  //세션 끊어버리긴
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("chkLogin",chkLogin);
		mv.setViewName("member/doLogin");
		
		return mv;
	}
}
