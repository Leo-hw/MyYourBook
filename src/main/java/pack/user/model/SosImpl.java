package pack.user.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.InqueryBean;
import pack.model.InqueryDto;

@Repository
public class SosImpl extends SqlSessionDaoSupport implements SosInter{

	@Autowired
	public SosImpl(SqlSessionFactory factory) {
			setSqlSessionFactory(factory);
	}
	
	@Override
	public boolean insertInquery(InqueryBean bean) {
		try {
			getSqlSession().insert("insertInquery", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertInquery err : " +e);
			return false;
		}
	}
	@Override
	public List<InqueryDto> inqlist(String inq_id) {
		
		return getSqlSession().selectList("selectInqList", inq_id);
	}
	
}
