package com.example.demo.members;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupDto {

	@NotEmpty(message = "이름은 필수입니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입니다.")
	private String password;
	

	private UserFaction faction;

	
	
}
