package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.OrderInfoBean;
import pack.model.OrderInfoDto;
@Repository
public class OrderInfoImpl extends SqlSessionDaoSupport implements OrderInfoInter{

	public OrderInfoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
		
	@Override
	public boolean buyNewBookUser(OrderInfoBean bean) {
		try {
			getSqlSession().insert("buyNewBookUser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("buyNewBookUser error : " + e); 		
			return false;
		}
	}
	@Override
	public boolean buyNewBookUnuser(OrderInfoBean bean) {
		try {
			getSqlSession().insert("buyNewBookUnuser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("buyNewBookUnuser error : " + e); 		
			return false;
		}
	}
	
	@Override
	public OrderInfoDto unmemberOrder(OrderInfoBean bean) {
		return getSqlSession().selectOne("unmemberOrder", bean);
	}
	
	@Override
	public OrderInfoDto getOrderbyPass(String order_passwd) {
		return getSqlSession().selectOne("getOrderbyPass", order_passwd);
	}
}
