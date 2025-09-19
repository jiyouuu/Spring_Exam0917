package com.example.demo.review;


import com.example.demo.book.Book;
import com.example.demo.members.Member;
import com.example.demo.reviewLike.LikeResponseDto;

public interface ReviewService {
	public void writeReview(Book book, String content, Member member);
	public Review findReview(Long id);
	public LikeResponseDto recommend(Review review, Member member);
	public Review modifyReview(Long id, ReviewRequestDto requestDto);
	public void deleteReview(Long id, Member member);

}
