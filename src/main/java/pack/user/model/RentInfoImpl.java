package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.RentInfoBean;
import pack.model.RentInfoDto;
@Repository
public class RentInfoImpl extends SqlSessionDaoSupport implements RentInfoInter{
	public RentInfoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public boolean rentOldBook(RentInfoBean bean) {
		try {
			getSqlSession().insert("rentOldBook", bean);
			return true;
		}catch (Exception e) {
			System.out.println("rentOldBook" + e);
			return false;
		}
	}
	
	@Override
	public RentInfoDto getRentInfo(String rent_id) {
		return getSqlSession().selectOne("getRentInfo",rent_id);
	}
}
