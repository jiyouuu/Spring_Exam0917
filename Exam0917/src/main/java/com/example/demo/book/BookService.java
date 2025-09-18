package com.example.demo.book;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.members.Member;
import com.example.demo.members.MemberRepository;
import com.example.demo.review.Review;
import com.example.demo.review.ReviewRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final ReviewRepository reviewRepository;
	
	// 책 등록
	public void bookRegister(BookDto bookDto) {
		Book newBook = new Book(bookDto.getTitle(), bookDto.getAuthor());
		this.bookRepository.save(newBook);
	}

	public Page<Book> findAll(Pageable pageable) {
		return this.bookRepository.findAll(pageable);
	}


	public Book findBook(Long id) {
		return this.bookRepository.findById(id).get();
	}
	
	
	public Page<Book> searchBooks(String searchType, String keyword, Pageable pageable) {
		
		// 검색어 없으면 그냥 페이징만 처리
		if(keyword.trim().isEmpty() || keyword == null) {
			// Spring Data JPA에서는 **JpaRepository / PagingAndSortingRepository**에서 제공하는 findAll(Pageable pageable) 메서드가 
			// 자동으로 페이징을 지원하도록 되어있음 
			return this.bookRepository.findAll(pageable);
		}
		
		String lower_Keyword = keyword.trim().toLowerCase(); // 공백 제거 및 소문자로 !! 
		
		if("title".equals(searchType)) {
			return this.bookRepository.findByTitleContaining(lower_Keyword, pageable);
		} else if("author".equals(searchType)) {
			Page<Book> a = this.bookRepository.findByAuthorContaining(lower_Keyword, pageable);
			System.out.println(a);
			return this.bookRepository.findByAuthorContaining(lower_Keyword, pageable);
			
		} else {
			return this.bookRepository.findAll(pageable);
		}
		
	}

	// '논쟁적인 도서' 조회 로직.
	// '댕댕파'와 '냥냥파' 양쪽 모두에게서 서평을 받은 도서를 찾아야 함 
	// 아래 부분을 bookrepository에서 nativeQuery를 통해 진행함
//	public List<Book> findcontroversialBooks() {
//		List<Book> books = this.bookRepository.findAll();
//		List<String> factions = new ArrayList<>();
//		List<Book> conversialBooks = new ArrayList<>();
//		
//		// 책 목록에서 
//		for(Book b : books) {
//
//			List<Review> reviews = b.getReviewList();
//			for(Review r : reviews) {
//				if(!factions.contains(r.getMember().getFaction().getValue())) {
//					factions.add(r.getMember().getFaction().getValue());
//				} 
//				if(factions.contains("DOG") && factions.contains("CAT") && !conversialBooks.contains(b)) {
//					conversialBooks.add(b);
//				}
//			}
//		}
//		
//		return conversialBooks;
//	}




	
	

	
	
	
}
