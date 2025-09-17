package com.example.demo.members;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

	USER("ROLE_MEMBER"),
	ADMIN("ROLE_ADMIN");
	
	private final String value;
}
