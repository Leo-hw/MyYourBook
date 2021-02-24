package pack.user.model;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.OldBookBean;
import pack.model.OldBookDto;

@Repository
public class OldBookImpl extends SqlSessionDaoSupport implements OldBookInter{

	@Autowired
	public OldBookImpl(SqlSessionFactory factory) {
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
	
	@Override
	public OldBookDto bookInfo(String book_no) {
		return getSqlSession().selectOne("oldbookinfo", book_no);
	}
	//oldbookinfo2
	@Override
	public OldBookDto rentalInfo(String book_no) {
		return getSqlSession().selectOne("oldbookinfo2", book_no);
	}
	
	@Override
	public OldBookDto getAllOldBook() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean readcnt(int readcnt) {
		try {
			getSqlSession().update("readcntUpdate", readcnt);
			return true;
		} catch (Exception e) {
			System.out.println("readcnt error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public OldBookDto view(String book_no) {
		return getSqlSession().selectOne("viewoldbook", book_no);
	}
	
	@Override
	public boolean updateRentOldBook(String book_no) {
		try {
			getSqlSession().update("updateRentOldBook", book_no);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean update(int ob_no) {
		try {
			getSqlSession().update("updateOldbook", ob_no);
			return true;
		} catch (Exception e) {
			System.out.println("update error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
}
