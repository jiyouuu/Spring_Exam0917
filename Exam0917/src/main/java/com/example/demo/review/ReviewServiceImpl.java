package com.example.demo.review;


import org.springframework.stereotype.Service;

import com.example.demo.book.Book;
import com.example.demo.members.Member;
import com.example.demo.reviewLike.LikeResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

	private final ReviewRepository reviewRepository;
	
	@Override
	public void writeReview(Book book, String content, Member member) {
		Review review = new Review(content, member,book);
		this.reviewRepository.save(review);
	}

	@Override
	public Review findReview(Long id) {
		return this.reviewRepository.findById(id).get();
	}

	@Override
	public LikeResponseDto recommend(Review review, Member member) {
		boolean liked;
		// 추천을 기존에 했는지 안했는지 보내야 함 
		if(review.getRecommendation().contains(member)) {  //만약에 이미 추천을 했던 멤버라면
				review.getRecommendation().remove(member);
				review.decreaseLikeCount();
				liked = false;		
		} else {
				review.getRecommendation().add(member);
				review.increaseLikeCount();    // 추천 수 증가 
				liked = true;
		}
		this.reviewRepository.save(review);
		return new LikeResponseDto(review.getLikeCount(), liked);
	}

	

	
	

}
