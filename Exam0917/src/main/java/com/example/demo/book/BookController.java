package com.example.demo.book;


import java.security.Principal;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.members.Member;
import com.example.demo.members.MemberRepository;
import com.example.demo.review.Review;
import com.example.demo.review.ReviewServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	private final MemberRepository memberRepository;
	
	private final ReviewServiceImpl  reviewService;
	
	// 도서 목록 조회 (페이징, 검색 포함), 상세 페이지 요청 처리.
	// 로그인해야만 책 목록 조회, 검색, 서평 읽기/쓰기가 가능하다.

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public String main(@RequestParam(value = "page", defaultValue ="0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size,
			Model model, Principal principal) {
		
		// Pageable : page + size + 정렬 정보 묶어서 전달  (페이징 정보)
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
				
		Page<Book> books = this.bookService.findAll(pageable);
		
		// **DTO 초기화** → 검색 폼에서 null 오류 방지
		model.addAttribute("bookSearchDto", new BookSearchDto());
		model.addAttribute("paging", books);
		
		Member m = this.memberRepository.findByUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
		model.addAttribute("currentUser", m);
		return "main";
	}

	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/books")
	public String search(@RequestParam(value = "page", defaultValue ="0") int page,
						@RequestParam(value = "size", defaultValue = "5") int size,
						@ModelAttribute BookSearchDto bookSearchDto, 
						Model model,  Principal principal) {
		
		
		String searchType = bookSearchDto.getSearchType();
		String keyword = bookSearchDto.getKeyword();

		// Pageable : page + size + 정렬 정보 묶어서 전달  (페이징 정보)
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		

		// DB에서 검색 + 페이징
		Page<Book> books = this.bookService.searchBooks(searchType, keyword, pageable);

		Member m = this.memberRepository.findByUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
		model.addAttribute("currentUser", m);
		model.addAttribute("paging", books);
		model.addAttribute("bookSearchDto", bookSearchDto);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		return "main";
		
		
	}


	// 서평 넣기 (어떤 책에, 어떤 리뷰를 , 누가?)
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/books/{id}/reviews")
	public String writeReview(@PathVariable(value = "id") Long id, @RequestParam(value = "content") String content, Principal principal) {

		try {
			Book book = this.bookService.findBook(id);
			Member member = this.memberRepository.findByUsername(principal.getName())
					.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
			
			this.reviewService.writeReview(book, content, member);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	
	
}

















