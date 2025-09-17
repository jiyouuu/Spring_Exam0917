package com.example.demo.review;

import com.example.demo.members.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {

	// 화면에 서평 정보를 보여줄 때 사용할 객체 
	// (서평 내용, 작성자, 공감 수, 현재 로그인 유저의 공감 여부 등).
	
	private String content;
	
	private Member member;
	
}
