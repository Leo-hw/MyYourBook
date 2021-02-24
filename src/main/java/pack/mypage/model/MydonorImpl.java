package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.OldBookDto;

@Repository
public class MydonorImpl extends SqlSessionDaoSupport implements MydonorInter{
	
	@Autowired
	public MydonorImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<OldBookDto> donorlistall() {
		return getSqlSession().selectList("donorlistall");
	}
	@Override
	public List<OldBookDto> donorListbyId(String id) {
		return getSqlSession().selectList("selectGiveList", id);
	}
}
