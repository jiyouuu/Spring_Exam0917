package com.example.demo.book;

import java.util.List;
import java.util.Set;

import com.example.demo.members.Member;
import com.example.demo.review.Review;

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
	
	// mappedBy(속성명) = "book" (속성값)
	// -> Review 엔터티의 book 필드에 의해 관계가 관리됨을 명시 
	// Book 쪽의 @OneToMany는 단순히 "읽기 전용(조회용)" 역할
	// 한개의 book에 여러개의 review를 추가할 수 있음. 
	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
	private List<Review> reviewList;
	

	
}
