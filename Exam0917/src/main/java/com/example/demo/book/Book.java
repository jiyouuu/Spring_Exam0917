package com.example.demo.book;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Builder
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
}
