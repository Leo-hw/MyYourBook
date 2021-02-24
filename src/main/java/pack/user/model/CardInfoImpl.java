package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.CardInfoBean;
import pack.model.CardInfoDto;

@Repository
public class CardInfoImpl extends SqlSessionDaoSupport implements CardInfoInter{
	public CardInfoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	// 회원가입할 때 카드 등록
	@Override
	public boolean insertCard(CardInfoBean bean) {
		try {
			getSqlSession().insert("insertCard", bean);
			return true;
		}catch (Exception e) {
			System.out.println("insertCard" + e);
			return false;
		}
	}
	
	//결제 창에서 회원일 시 등록된 카드 가져오기
	@Override
	public CardInfoDto selectCard(String card_ownerid) {
		return getSqlSession().selectOne("selectCard", card_ownerid);
	}
}
