package pack.user.model;

import java.util.List;

import pack.controller.InqueryBean;
import pack.model.InqueryDto;

public interface SosInter {

	//1:1 문의하기(고객)
	public boolean insertInquery(InqueryBean bean);
	
	//1:1문의 내역 보기
	public List<InqueryDto> inqlist(String inq_id); 
}
