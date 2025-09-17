package com.example.demo.members;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(MemberSignupDto memberSignupDto) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid MemberSignupDto memberSignupDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		
		this.memberService.signUp(memberSignupDto);
		return "redirect:/login";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
