package com.example.demo.book;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	// 도서 목록 조회 (페이징, 검색 포함), 상세 페이지 요청 처리.
	
	// 로그인해야만 책 목록 조회, 검색, 서평 읽기/쓰기가 가능하다.
	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public String main() {
		return "main";
	}
	
	
	
	
}
