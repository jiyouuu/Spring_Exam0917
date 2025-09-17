package com.example.demo.members;

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

}
