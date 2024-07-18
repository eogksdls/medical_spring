package com.java.sevice;

import com.java.dto.MemberDto;

public interface MemberService {
	
	//로그인 확인
	MemberDto selectLogin(MemberDto memberDto);

}
