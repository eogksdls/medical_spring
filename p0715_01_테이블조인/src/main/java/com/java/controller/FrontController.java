package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.DepartDto;
import com.java.dto.EDDto;
import com.java.dto.EmpDto;
import com.java.dto.Emp_DepartDto;
import com.java.service.DepartService;
import com.java.service.EDService;
import com.java.service.EmpService;
import com.java.service.Emp_DepartService;

@Controller
public class FrontController {
	
	@Autowired EmpService empService;
	@Autowired DepartService departService;
	@Autowired Emp_DepartService emp_departService;
	@Autowired EDService edService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	// 사원리스트
	@RequestMapping("/emp_list")
	public ModelAndView emp_list() {
		
		ArrayList<EmpDto> list = empService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("emp_list");
		
		return mv;
	}
	
	//부서리스트
	@RequestMapping("/depart_list")
	public ModelAndView depart_list() {
		
		ArrayList<DepartDto> list = departService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("depart_list");
		
		return mv;
	}
	
	//사원+부서리스트1
	@RequestMapping("/emp_depart_list")
	public ModelAndView emp_depart_list(){
		
		ArrayList<Emp_DepartDto> list = emp_departService.selectAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("emp_depart_list");
		return mv;
		
	}
	
	//사원+부서리스트2
	@RequestMapping("/ed_list")
	public ModelAndView ed_list() {
		
		ArrayList<EDDto> list = edService.selectAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("ed_list");
		
		return mv;
	}
	
}
