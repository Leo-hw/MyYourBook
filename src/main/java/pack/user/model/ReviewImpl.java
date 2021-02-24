package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.model.ReviewDto;
import pack.controller.ReviewBean;
@Repository
public class ReviewImpl extends SqlSessionDaoSupport implements ReviewInter{
	
	public ReviewImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	//리뷰 목록
	@Override
	public List<ReviewDto> selectNewbookReviewList(int nb_no) {
		return getSqlSession().selectList("selectNewbookReviewList", nb_no);
	}
	//리뷰작성
	@Override
	public boolean insertNewbookReview(ReviewBean bean) {
		try {
			getSqlSession().insert("insertNewbookReview", bean);
			return true;
		}catch (Exception e) {
			System.out.println("insertNewbookReview" + e);
			return false;
		}
	}
	
	
	@Override
	public ReviewDto selectNewbookReview(int review_no) {
		return getSqlSession().selectOne("selectNewbookReview", review_no);
	}
	//리뷰 추천
	@Override
	public boolean plusGonggam(int review_no) {
		try {
			getSqlSession().update("plusGonggam", review_no);
			return true;
		}catch (Exception e) {
			System.out.println("plusGonggam" + e);
			return false;
		}
	}
	@Override
	public boolean deleteReview(int review_no) {
		try {
			getSqlSession().delete("deleteReview", review_no);
			return true;
		}catch (Exception e) {
			System.out.println("plusGonggam" + e);
			return false;
		}
	}
}
