package pack.mypage.model;

import java.util.List;

import pack.controller.RentInfoBean;
import pack.controller.UserBean;
import pack.model.OldBookDto;

public interface MyrentInter {

	//최근 대여 내역 3 추상메소드
	public List<OldBookDto> rentlistall(String userid);
	
	OldBookDto getObPrice(int ob_no);
	boolean deleteRentinf(int rent_no);
	boolean delpointuser(UserBean bean);
	boolean upObProcess(int rent_no);
	boolean updateState(int rent_no);
}
