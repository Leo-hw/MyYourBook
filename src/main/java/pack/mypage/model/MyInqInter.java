package pack.mypage.model;

import java.util.List;

import pack.model.InqueryDto;

public interface MyInqInter {

	// 전체 문의내역 불러오기
	public List<InqueryDto> inqListAll(String user_id);
}
