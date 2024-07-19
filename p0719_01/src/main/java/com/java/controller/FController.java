package com.java.controller;

import java.net.HttpURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.OAuthTokenDto;

@Controller
public class FController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/kakao/oauth")
	@ResponseBody  //데이터 넘겨주기
	public String oauth(String code) {
		
		// [ 카카오 로그인 ]
		//#### 1차 : code ####--------------------------------------------------
		System.out.println("oauth code : "+code);
		String grant_type = "authorization_code";
		String client_id = "f695cebca11264fb77faae7cb782cf59";
		String redirect_uri = "http://localhost:8181/kakao/oauth";
		
		//공공데이터에서 url연결해서 데이터 받아오기
//		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
		
		// post방식으로 전송 - daum 토큰키를 요청함
		RestTemplate rt = new RestTemplate(); //객체선언
		// header 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		// 위 데이터를 1개로 묶음처리
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		//header 오브젝트, MultiValueMap를 1개 오브젝트로 묶음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		//Http 요청
		String oauthUrl = "https://kauth.kakao.com/oauth/token";
		
		//rt => 브라우저에 url을 넣어주는 역할
		ResponseEntity<String> response = rt.exchange(oauthUrl, 
				HttpMethod.POST, kakaoTokenRequest, String.class);
		
		System.out.println("response : "+response);
		
		//#### 2차 : code ####-------------------------------------------------
		
		// 카카오에서 response로 받은 json 데이터를 
		// 자바 dto 파일에 저장시킴
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthTokenDto oAuthTokenDto = null;
		
		try {
			oAuthTokenDto = objectMapper.readValue(response.getBody(),OAuthTokenDto.class);
		} catch (Exception e) {e.printStackTrace(); } 
			
		System.out.println("oAuthTokenDto Access_token : "+oAuthTokenDto.getAccess_token());
		
		// #### 3차 : 사용자 정보 요청하기 ####
		
		// post방식으로 전송 - daum 토큰키를 요청함
		RestTemplate rt2 = new RestTemplate(); //객체선언
		// header 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer "+oAuthTokenDto.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		// 파라미터 값은 필요없음
		
		//header 오브젝트, MultiValueMap를 1개 오브젝트로 묶음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = 
				new HttpEntity<>(headers2);
		
		//사용자 정보 Http 요청
		String oauthUrl2 = "https://kapi.kakao.com/v2/user/me";
		
		//rt => 브라우저에 url을 넣어주는 역할
		ResponseEntity<String> response2 = rt2.exchange(oauthUrl2, 
				HttpMethod.POST, kakaoTokenRequest2, String.class);
		
		
		// #### 끝 ####
		//json parser 시작
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoDto kakaoDto = null;
		try {
			kakaoDto = objectMapper2.readValue(response2.getBody(),KakaoDto.class);
		} catch (Exception e) {e.printStackTrace(); } 
			
//		System.out.println("kakaoDto 개인정보 닉네임 : "+kakaoDto.getProperties().getNickname());
		System.out.println("response 개인정보 : "+response2);
		
		return "카카오 개인정보 응답 : "+response2;  //response = 토큰키
		
	}
	
	
	
	
	
	
	
	
}
