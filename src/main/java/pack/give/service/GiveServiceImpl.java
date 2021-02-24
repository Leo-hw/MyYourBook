package pack.give.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pack.controller.OldBookBean;
import pack.give.dao.GiveDao;
import pack.give.util.FileUtils;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.UserInter;

@Service("giveService")
public class GiveServiceImpl extends SqlSessionDaoSupport implements GiveService {
	Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Resource(name = "giveDao")
	private GiveDao giveDao;

	@Inject
	private SqlSession sqlSession;

	@Autowired
	private HttpSession session;
	
	@Resource
	private UserInter userInter;
	/*
	 * @Autowired protected SqlSessionTemplate sqlSession;
	 * 
	 * private static String namespace = "pack.give.dao.GiveDao";
	 */
	@Autowired
	public GiveServiceImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
		
	}

	@Override
	public void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception {
		giveDao.insertOldBook(map);
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for (int i = 0; i < list.size(); i++) {
			giveDao.insertFile(list.get(i));
		}
	}
	
	@Override
	public OldBookDto getGiveList(String ob_userid) throws Exception{
		return (OldBookDto) giveDao.getGiveList(ob_userid);
	}
	
}
