package com.example.demo.members;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void signUp(MemberSignupDto memberSignupDto) {
		// 햄찌로 회원가입할 경우에만 admin으로 가입 시킴
		if(memberSignupDto.getUsername().equals("햄찌")) {
			Member m = new Member(memberSignupDto.getUsername(), passwordEncoder.encode(memberSignupDto.getPassword())
					,UserRole.ADMIN, memberSignupDto.getFaction());
			this.memberRepository.save(m);
		}
		else {
			Member m = new Member(memberSignupDto.getUsername(), passwordEncoder.encode(memberSignupDto.getPassword())
					,UserRole.USER, memberSignupDto.getFaction());
			this.memberRepository.save(m);
		}
		
	}

}

