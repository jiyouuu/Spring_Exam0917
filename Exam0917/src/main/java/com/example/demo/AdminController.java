package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.book.Book;
import com.example.demo.book.BookDto;
import com.example.demo.book.BookRepository;
import com.example.demo.book.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final BookService bookService;
	private final BookRepository bookRepository;
	
	// 관리자 페이지, '논쟁적인 도서' 리포트 조회 요청 처리. + 책을 새로 등록
	// 책 등록 
	@PostMapping("/books")
	public String bookRegister(@Valid BookDto bookDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/admin";
		}
		this.bookService.bookRegister(bookDto);
		return "redirect:/admin";
	}
	
	// '논쟁적인 도서' 리포트 조회 요청 처리. 
	// '댕댕파'와 '냥냥파' 양쪽 모두에게서 서평을 받은 도서
	@GetMapping
	public String adminPage(BookDto bookDto, Model model) {
		List<Book> controversialBooks = this.bookRepository.findControversialBooks();

		model.addAttribute("controversialBooks", controversialBooks);
			return "admin";
	}
	
}






