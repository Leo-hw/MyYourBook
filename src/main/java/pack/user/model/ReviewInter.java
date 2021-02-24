package pack.user.model;

import java.util.List;

import pack.model.ReviewDto;
import pack.controller.ReviewBean;

public interface ReviewInter {
	//해당 책 리뷰 최신순으로 정렬
	public List<ReviewDto> selectNewbookReviewList(int nb_no);
	//해당 책 리뷰 추가
	public boolean insertNewbookReview(ReviewBean bean);
	//해당 책의 특정 리뷰 추천
	public ReviewDto selectNewbookReview(int review_no);
	public boolean plusGonggam(int review_no);
	
	//해당 책의 특정 리뷰 삭제
	public boolean deleteReview(int review_no);
	
}
