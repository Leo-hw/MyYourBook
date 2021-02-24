package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.InqueryDto;

@Repository
public class MyInqImpl extends SqlSessionDaoSupport implements MyInqInter{

	@Autowired
	public MyInqImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<InqueryDto> inqListAll(String user_id) {
		
		return getSqlSession().selectList("selectInqList", user_id);
	}
}
