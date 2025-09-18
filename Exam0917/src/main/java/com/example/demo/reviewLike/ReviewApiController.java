package com.example.demo.reviewLike;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.members.Member;
import com.example.demo.members.MemberRepository;
import com.example.demo.review.Review;
import com.example.demo.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewApiController {
	
	private final ReviewService reviewService;
	private final MemberRepository memberRepository;
	
	// 서평 '공감/공감 취소' API 요청 처리.
	//  await fetch(`/api/reviews/${reviewId}/like`
	@PostMapping("reviews/{id}/like")
	public ResponseEntity<LikeResponseDto> reviewRecommend(@PathVariable(value = "id") Long id, Principal principal){
		Review review = this.reviewService.findReview(id);
		Member member = this.memberRepository.findByUsername(principal.getName())
				.orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다."));
		
		// 추천 처리  (추천 수 증가)
		// 해당 게시글에 대한 추천수를 +1 시켜야 함
		LikeResponseDto responseDto = this.reviewService.recommend(review, member);

		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	
	}
	
}







