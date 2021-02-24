package pack.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.OldBookBean;

@Repository
public class OldBookDao extends SqlSessionDaoSupport{

	@Autowired
	public OldBookDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public boolean insertOldBook(OldBookBean bean){
		boolean b= false;
				try {
					getSqlSession().insert("insertOldBook");
					b = true;
				} catch (Exception e) {
					// TODO: handle exception
					b= false;	
				}
				return b;
	}
}
