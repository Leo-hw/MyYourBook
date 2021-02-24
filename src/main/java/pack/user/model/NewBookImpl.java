package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.NewBookBean;
import pack.model.NewBookDto;

@Repository
public class NewBookImpl extends SqlSessionDaoSupport implements NewBookInter{
	
	public NewBookImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
		
	@Override
	public List<NewBookDto> getBookAll() {
		return getSqlSession().selectList("selectBookAll");
	}
	//메인화면에 뿌릴 내용
	@Override
	public NewBookDto getBestSeller() {
		return getSqlSession().selectOne("selectBestseller");
	}
	
	@Override
	public List<NewBookDto> getGenre(String nb_genre) {
		return getSqlSession().selectList("selectGenre", nb_genre);
	}
	
	@Override
	public List<NewBookDto> getBest() {
		return getSqlSession().selectList("selectBest30");
	}
	
	@Override
	public List<NewBookDto> getNew() {
		return getSqlSession().selectList("selectNew");
	}
	
	// 책 세부내용 newbook.jsp
	@Override
	public NewBookDto selectNewbook(int nb_no) {
		return getSqlSession().selectOne("selectNewbook", nb_no);
	}
	
	@Override
	public List<NewBookDto> selectAuthorList(String nb_authour) {
		return getSqlSession().selectList("selectAuthorList", nb_authour);
	}
	
	//새책 조회수 올리기
	@Override
	public boolean plusReadCnt(int nb_no) {
		try {
			getSqlSession().update("plusReadCnt", nb_no);
			return true;
		}catch (Exception e) {
			System.out.println("plusReadCnt" + e);
			return false;
		}
	}
	
}
