package pack.user.model;

import pack.model.UserDto;
import pack.controller.UserBean;


public interface UserInter {
	// 유저 아이디 비교하기
	public UserDto selectUser(String user_id);
	
	// 회원가입
	public boolean insertUser(UserBean userbean);
	
	//아이디 체크
	public int checkUserId(String user_id);
	
	//결제할떄 포인트사용시 차감
	public boolean usePoint(UserBean bean);
	
	
	//대여할 때 1000포인트 차감
	public boolean minusRentPoint(String user_id);
}
