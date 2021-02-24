package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.model.OldBookDto;
import pack.model.UserDto;

@Repository
public class OldBookListImpl extends SqlSessionDaoSupport implements OldBookListInter{
	
	public OldBookListImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	@Override
	public List<OldBookDto> selectGenre(String ob_genre) {
		return getSqlSession().selectList("oldGenre", ob_genre);
	}
	
@Override
	public List<OldBookDto> selectGenre2(String ob_genre) {
		return getSqlSession().selectList("oldGenre2", ob_genre);
	}
	
	@Override
	public List<OldBookDto> rentmain() {
		return getSqlSession().selectList("oldRandom");
	}
	
	@Override
	public List<OldBookDto> rentmain2() {
		return getSqlSession().selectList("oldlow");
	}
	
	@Override
	public List<OldBookDto> selectHighAll() {
		return getSqlSession().selectList("oldHigh");
	}
	@Override
	public List<OldBookDto> selectLowAll() {
		return getSqlSession().selectList("oldLow");
	}
	
	@Override
	public OldBookDto bestOne() {
		return getSqlSession().selectOne("selectBestRentBook");
	}
	
	@Override
	public UserDto bestRead() {
		return getSqlSession().selectOne("selectBestRead");
	}
	
}
