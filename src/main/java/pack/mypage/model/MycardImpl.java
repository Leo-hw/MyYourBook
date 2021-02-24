package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.CardInfoDto;

@Repository
public class MycardImpl extends SqlSessionDaoSupport implements MycardInter {
	
	@Autowired
	public MycardImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<CardInfoDto> cardlistall() {
		return getSqlSession().selectList("cardlistall");
	}
	
	
}
