package pack.user.model;

import pack.controller.RentInfoBean;
import pack.model.RentInfoDto;

public interface RentInfoInter {
	
	// 책 대여하기 눌렀을 때
	public boolean rentOldBook(RentInfoBean bean);
	
	// 방금 빌린 대여 정보
	public RentInfoDto getRentInfo(String rent_id);
}
