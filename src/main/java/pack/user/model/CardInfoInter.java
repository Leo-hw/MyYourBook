package pack.user.model;

import pack.controller.CardInfoBean;
import pack.model.CardInfoDto;

public interface CardInfoInter {
	//회원가입 시 카드 등록
	public boolean insertCard(CardInfoBean bean);
	
	//결제 창으로 들어갈 때 카드 가져오기
	public CardInfoDto selectCard(String card_ownerid);
}
