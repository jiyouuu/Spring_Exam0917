package com.example.demo.review;

import com.example.demo.members.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
	public ReviewResponseDto(String content, Member member) {
		this.content  = content;
		this.member = member;
	}
	private String content;
	private Member member;
}
