package com.example.demo.reviewLike;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class LikeResponseDto {  // 공감 API의 응답으로 사용할 객체 (변경된 총 공감 수, 현재 공감 상태).

	public LikeResponseDto(Integer likeCount, boolean liked) {
		this.likeCount = likeCount;
		this.liked = liked;
	}

	private Integer likeCount;   // 총 공감 수
	private boolean liked;  // 현재 사용자의 공감 상태 
	
}
