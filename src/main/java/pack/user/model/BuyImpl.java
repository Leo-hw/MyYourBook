package pack.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.model.DataSource;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.UserDto;

@Repository
public class BuyImpl extends SqlSessionDaoSupport implements BuyInter{
	public BuyImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	
	@Override
	public UserDto point(String user_id) {
		return getSqlSession().selectOne("viewpoint", user_id);
	}
	
	
	@Override
	public OrderInfoDto show(String order_person) {
		return getSqlSession().selectOne("view", order_person);
	}
	
	
	
}
