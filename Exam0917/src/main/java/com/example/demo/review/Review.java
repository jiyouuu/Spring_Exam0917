package com.example.demo.review;

import java.util.Set;

import com.example.demo.book.Book;
import com.example.demo.members.Member;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private String content;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	@Builder
	public Review(String content, Member member, Book book) {
		this.content = content;
		this.member = member;
		this.book = book;
	}
	
	
	// 공감 : 한명의 사용자가 따봉을 난사한다면 ? 추천 조작. 
	// -> 중복 방지로 Set을 씀 
	@ManyToMany
	Set<Member> recommendation; 
	
	// 추천수 
	private Integer likeCount = 0;
	
	public void increaseLikeCount() {
		this.likeCount++;
	}

	public void decreaseLikeCount() {
		this.likeCount--;	
	}
	
	
	public void update(String content) {
		this.content = content;
	}

	
}

