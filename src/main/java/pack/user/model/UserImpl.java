package pack.user.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


import pack.model.UserDto;
import pack.controller.UserBean;



@Repository
public class UserImpl extends SqlSessionDaoSupport implements UserInter{
	
	

	public UserImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	
	// 로그인
	@Override
	public UserDto selectUser(String user_id) {
		return getSqlSession().selectOne("selectUser", user_id);
	}
	
	// 회원가입
	
	@Override
	public boolean insertUser(UserBean userbean) {
		try {
			getSqlSession().insert("insertUser", userbean);
			return true;
		}catch (Exception e) {
			System.out.println("insertUser" + e);
			return false;
		}
	}
	
	
	@Override
	public int checkUserId(String user_id) {
		return getSqlSession().selectOne("checkUserId", user_id);
	}
	
	
	@Override
	public boolean usePoint(UserBean bean) {
		try {
			getSqlSession().update("usePoint", bean);
			return true;
		}catch (Exception e) {
			System.out.println("usePoint" + e);
			return false;
		}
	}
	
	@Override
	public boolean minusRentPoint(String user_id) {
		try {
			getSqlSession().update("minusRentPoint", user_id);
			return true;
		}catch (Exception e) {
			System.out.println("minusRentPoint" + e);
			return false;
		}
	}
}
