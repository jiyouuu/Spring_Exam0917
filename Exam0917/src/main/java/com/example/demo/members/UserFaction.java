package com.example.demo.members;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserFaction {

	DOG("DOG"),
	CAT("CAT");
	
	private final String value;
}
