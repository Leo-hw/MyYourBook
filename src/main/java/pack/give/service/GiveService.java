package pack.give.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pack.controller.OldBookBean;
import pack.give.dao.GiveDao;
import pack.model.OldBookDto;

public interface GiveService {
		
	void insertOldBook(Map<String, Object> map, HttpServletRequest request) throws Exception;
	public OldBookDto getGiveList(String ob_userid) throws Exception;
	
	//public void insertOldData(OldBookBean bean) throws Exception;	
	
}
