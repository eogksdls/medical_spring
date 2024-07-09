package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.Students;

@Controller
@RequestMapping("/students")
public class StuController {
	
	@RequestMapping("/students")
	public String students() {
		return "students/students";
	}
	
	@RequestMapping("/doStudents")
	public ModelAndView requestMethodName(Students s) {
		Students stu = new Students(s.getStuNo(),s.getName(),
				s.getKor(),s.getEng(),s.getMath());  // 부분 생성자 다시 호출하여 total, avg 계산(1)
		stu.setGender(s.getGender()); //부분 생성자 set에 성별, 취미도 같이 넣어줘야함(3)
		stu.setHobby(Arrays.toString(s.getHobbys()));
		
		System.out.println("stuNo : "+stu.getStuNo());
		System.out.println("name : "+stu.getName());
		System.out.println("total : "+stu.getTotal());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("stu",stu); //데이터를 넘겨주는 생성자가 부분 생성자이므로..!(2)
		mv.setViewName("students/doStudents");
		
		return mv;
	}
	
	@RequestMapping("/stuUpdate")
	public ModelAndView stuUpdate(Students stu) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("stu",stu); //"stu"로 객체를 받아야함~
		mv.setViewName("students/stuUpdate");
		return mv;
	}
	
	
}
