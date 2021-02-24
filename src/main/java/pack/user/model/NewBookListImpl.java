package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.NewBookDto;

@Repository
public class NewBookListImpl extends SqlSessionDaoSupport implements NewBookListInter{
	
	@Autowired
	public NewBookListImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	
	@Override
	public List<NewBookDto> getBestSeller() {
		return getSqlSession().selectList("selectBestseller");
	}
	

	@Override
	public List<NewBookDto> selectReadTop3() {
		return getSqlSession().selectList("selectReadTop3");
	}
	
	@Override
	public List<NewBookDto> selectRandom10() {
		return getSqlSession().selectList("selectRandom10");
	}
	
	@Override
	public List<NewBookDto> selectNew() {
		return getSqlSession().selectList("selectNew");
	}
	
	@Override
	public List<NewBookDto> selectGenre(String genre) {
		return getSqlSession().selectList("selectGenre", genre);
	}
	
	@Override
	public List<NewBookDto> selectBest30() {
		return getSqlSession().selectList("selectBest30");
	}
	
	/*
	 * @Override public List<NewbookDto> selectAge() { return
	 * getSqlSession().selectList(""); }
	 */
	
	@Override
	public NewBookDto selectBest() {
		return getSqlSession().selectOne("selectBest");
	}
}
