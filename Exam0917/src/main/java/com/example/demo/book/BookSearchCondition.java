package com.example.demo.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchCondition {

	// 검색 조건을 담을 객체
	
	private String keywordType;
	
	private String keyword;
}
