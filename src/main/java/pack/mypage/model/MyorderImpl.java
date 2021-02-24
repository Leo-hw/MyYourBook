package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.NewBookBean;
import pack.model.NewBookDto;
import pack.model.OrderInfoDto;

@Repository
public class MyorderImpl extends SqlSessionDaoSupport implements MyorderInter{
	
	@Autowired
	public MyorderImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<OrderInfoDto> orderlistall(String user_id) {
		return getSqlSession().selectList("orderlistall", user_id);
	}
	
	@Override
	public List<OrderInfoDto> orderoldlistall(String user_id) {
		return getSqlSession().selectList("orderoldlistall", user_id);
	}
	
	@Override
	public List<OrderInfoDto> ordernewlistall(String user_id) {
		return getSqlSession().selectList("ordernewlistall", user_id);
	}
	
	@Override
	public NewBookDto recommandNewBook() {
		return getSqlSession().selectOne("recommandNewBook");
	}
	
	@Override
	public OrderInfoDto myorderinfo(String orderlist_no) {
		return getSqlSession().selectOne("myorderinfo", orderlist_no);
	}
	
	@Override
	public List<OrderInfoDto> myorderinfoall(String orderlist_no) {
		return getSqlSession().selectList("myorderinfoall", orderlist_no);
	}
	
	@Override
	public boolean deletemyorder(int order_no) {
		try {
			getSqlSession().delete("delmyord", order_no);
			return true;
		} catch (Exception e) {
			System.out.println("deletemyorder error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean upNbScount(NewBookBean bean) {
		try {
			getSqlSession().update("upnbc", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upNbScount error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
}
