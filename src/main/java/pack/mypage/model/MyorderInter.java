package pack.mypage.model;

import java.util.List;

import pack.controller.NewBookBean;
import pack.model.NewBookDto;
import pack.model.OrderInfoDto;

public interface MyorderInter {
	//전체 주문 내역 추상메소드
		public List<OrderInfoDto> orderlistall(String user_id);
		public List<OrderInfoDto> orderoldlistall(String user_id);
		public List<OrderInfoDto> ordernewlistall(String user_id);
		
		//랜덤 추천
		public NewBookDto recommandNewBook();
		
		public OrderInfoDto myorderinfo(String orderlist_no);
		public List<OrderInfoDto> myorderinfoall(String orderlist_no);
		
		// 주문정보 삭제
		boolean deletemyorder(int order_no);
		// 판매량, 재고 수정
		boolean upNbScount(NewBookBean bean);
	
}
