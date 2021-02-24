package pack.give.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.OldBookDto;

@Repository("giveDao")
public class GiveDao extends AbstractDAO{
	
	/*
	 * @SuppressWarnings("unchecked") public List<Map<String, Object>>
	 * selectGiveList(Map<String, Object> map)throws Exception{ return
	 * (List<Map<String, Object>>)selectList("selectGiveList"); }
	 */
	
	@Autowired
	SqlSession session;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertFile(Map<String, Object> map) throws Exception {
		insert("insertFile", map);
	}
	
	public void insertOldBook(Map<String, Object>map) throws Exception{
		insert("insertOldBook", map);
	}
	
	public List<OldBookDto> getGiveList(String ob_userid) {
		return session.selectList("selectGiveList",ob_userid);
	}
}
