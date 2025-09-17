package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, CustomBookRepository{
	// 검색 조건과 페이징 처리를 위한 CustomBookRepository를 상속받음.
}
