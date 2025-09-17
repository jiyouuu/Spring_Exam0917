package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.members.Member;
import com.example.demo.members.MemberRepository;
import com.example.demo.members.UserRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> m = this.memberRepository.findByUsername(username);
		if(m.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없음!");
		}
		
		Member member = m.get();
		
		List<GrantedAuthority> auth = new ArrayList<>();
		
		// 일단 이렇게 권한을 부여하는 것은 야매 방법이라 다음에 더 심화과정을 배울 것임.
		if(member.getRole().equals(UserRole.USER)) {
			auth.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
			// UserRole.USER.name()은 enum 상수의 이름(String)을 반환
		} else {
			auth.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}
			
	
		return new User(member.getUsername(), member.getPassword(), auth);
	}
}
