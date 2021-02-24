package pack.mypage.model;

import java.util.List;

import pack.model.CardInfoDto;
import pack.model.InqueryDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;

public interface MypageInter {
	
	//최근 주문 내역 3 추상메소드
	public List<OrderInfoDto> orderlist(String id);
	
	//최근 대여 내역 3 추상메소드
	public List<OldBookDto> rentlist(String id);
	
	//최근 기부 내역 3 추상메소드
	public List<OldBookDto> donorlist(String id);
	
	//최근 카드 내역 3 추상메소드
	public List<CardInfoDto> cardlist(String id);
	
	//최근 QnA 내역 3 추상메소드
	public List<InqueryDto> inqlist(String id);
	
	//랜덤 추천
	public NewBookDto recommandNewBook();
}
