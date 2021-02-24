package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.model.OldBookDto;
import pack.model.OrderInfoDto;

@Repository
public class CartImpl extends SqlSessionDaoSupport implements CartInter{
	public CartImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<OldBookDto> cartlist(String user_id) {
		return getSqlSession().selectList("cartList", user_id);
	}
	
	@Override
	public boolean delete(int order_no) {
		int result = getSqlSession().delete("deleteData", order_no);
		if(result > 0) {
			return true;
		}else {
			return false;				
				}
		}
	
	
}
