package pack.user.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.OrderInfoBean;
import pack.model.OrderInfoDto;

@Repository
public class UnmemberImpl extends SqlSessionDaoSupport implements UnmemberInter{
	public UnmemberImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public OrderInfoDto search(OrderInfoBean bean) {
		return getSqlSession().selectOne("unmembercheck", bean);
	}
	
}
