package pack.mypage.model;

import java.util.List;

import pack.model.CardInfoDto;

public interface MycardInter {
	//전체 카드 내역 추상메소드
	public List<CardInfoDto> cardlistall();
}
