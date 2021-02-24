package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.CardInfoDto;
import pack.model.InqueryDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;

@Repository
public class MypageImpl extends SqlSessionDaoSupport implements MypageInter{

	@Autowired
	public MypageImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	@Override
	public List<OrderInfoDto> orderlist(String id) {
		return getSqlSession().selectList("order3list", id);
	}
	
	@Override
	public List<OldBookDto> rentlist(String id) {
		return getSqlSession().selectList("rent3list", id);
	}
	
	@Override
	public List<OldBookDto> donorlist(String id) {
		return getSqlSession().selectList("donor3list", id);
	}
	
	@Override
	public List<CardInfoDto> cardlist(String id) {
		return getSqlSession().selectList("card3list", id);
	}
	
	@Override
	public List<InqueryDto> inqlist(String id) {
		return getSqlSession().selectList("inq3list", id);
	}
	
	@Override
	public NewBookDto recommandNewBook() {
		return getSqlSession().selectOne("recommandNewBook");
	}
}
