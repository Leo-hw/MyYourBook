package pack.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.OldBookBean;
import pack.controller.RentInfoBean;
import pack.controller.UserBean;
import pack.model.OldBookDto;

@Repository
public class MyrentImpl extends SqlSessionDaoSupport implements MyrentInter{
	
	@Autowired
	public MyrentImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<OldBookDto> rentlistall(String userid) {
		return getSqlSession().selectList("rentlistall", userid);
	}
	
	@Override
	public OldBookDto getObPrice(int ob_no) {
		return getSqlSession().selectOne("getobp", ob_no);
	}
	
	@Override
	public boolean deleteRentinf(int rent_no) {
		try {
			getSqlSession().update("delrinf", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("deleteRentinf error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean upObProcess(int rent_no) {
		try {
			getSqlSession().update("upobprocess", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("upObProcess error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean delpointuser(UserBean bean) {
		try {
			getSqlSession().update("delpointuser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("delpointuser error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean updateState(int rent_no) {
		try {
			getSqlSession().update("uprent", rent_no);
			return true;
		} catch (Exception e) {
			System.out.println("updateThrow error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
}
