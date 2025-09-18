package com.example.demo.members;

import java.util.Objects;



import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	// @Enumerated → 이 필드가 Enum 타입임을 JPA에 알려줌
	// EnumType.STRING → Enum 이름 그대로 DB에 저장
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Enumerated(EnumType.STRING)
	private UserFaction faction;
	
	@Builder
	public Member(String username, String password, UserRole role, UserFaction faction) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.faction = faction;
	}
	
	@Override
	public boolean equals(Object o) {
		// 객체 맨날 비교하기 귀찮아서 객체 비교를 위해 정의해두는 메서드 
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Member member = (Member) o;
		return Objects.equals(id, member.id);
	}
	
	@Override
	public int hashCode() {  // 검색속도 높일라고 썼다. 
	    return Objects.hash(id);
	}

}









