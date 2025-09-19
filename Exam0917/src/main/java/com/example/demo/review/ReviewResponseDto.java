package com.example.demo.review;

import com.example.demo.members.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
	public ReviewResponseDto(String content, Member member) {
		this.content  = content;
		this.username = member.getUsername();
	}
	private String content;
	private String username;  // 엔티티(Member)가 프록시라서 Jackson이 직렬화 실패 → 에러. 그래서 딱 필요한 필드만 받음. 
							  //  "엔티티가 프록시다"라는 말은 Hibernate(JPA 구현체)가 진짜 객체 대신 '대리인(프록시)' 객체를 리턴한다는 뜻
							  // 프록시란? Hibernate가 DB 접근을 미루기 위해 만든 "대리 객체".
							  // 왜 생김? 지연 로딩(LAZY) 때문에 → 필요할 때만 DB에서 꺼내도록.
							  // 왜 문제 됨? Jackson이 프록시 내부 구조를 JSON으로 직렬화 못해서.
							  // 해결 방법? 엔티티 자체 말고, 필요한 값만 DTO로 꺼내서 전달하기.
}
