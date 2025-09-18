package com.example.demo.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchDto {

	// 검색 조건을 담을 객체
	
	private String searchType;
	
	private String keyword;
}
