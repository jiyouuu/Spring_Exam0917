package com.example.demo.book;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long>{
	// 검색 조건과 페이징 처리를 위한 쿼리
	// DB에서 바로 검색 + 페이징 + 정렬
	@Query(value = "SELECT * FROM BOOK WHERE TITLE LIKE %:keyword%", nativeQuery = true)
	Page<Book> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
	
	
	@Query(value = "SELECT * FROM BOOK WHERE AUTHOR LIKE %:keyword%", nativeQuery = true)
	Page<Book> findByAuthorContaining(@Param("keyword") String keyword,  Pageable pageable);
	
	
	// '댕댕파'와 '냥냥파' 양쪽 모두에게서 서평을 받은 도서를 찾는 QUERY
	@Query(value = "SELECT b.* FROM BOOK B JOIN REVIEW R ON B.ID = R.BOOK_ID "
			+ "JOIN MEMBER M ON R.MEMBER_ID = M.ID "
			+ "WHERE M.FACTION IN ('DOG', 'CAT') "
			+ "GROUP BY B.ID "
			+ "HAVING COUNT(DISTINCT M.FACTION) = 2", nativeQuery = true)
	List<Book> findControversialBooks();
}
