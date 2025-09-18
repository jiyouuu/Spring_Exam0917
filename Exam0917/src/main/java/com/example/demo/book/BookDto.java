package com.example.demo.book;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

	@NotEmpty(message = "제목은 필수입니다.")
	private String title;
	
	@NotEmpty(message = "저자는 필수입니다.")
	private String author;
}
