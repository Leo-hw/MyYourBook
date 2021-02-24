package pack.user.model;


import pack.controller.OrderInfoBean;
import pack.model.OrderInfoDto;

public interface OrderInfoInter {
	// 단일 구매 페이지
	boolean buyNewBookUser(OrderInfoBean bean);
	boolean buyNewBookUnuser(OrderInfoBean bean);
	
	// 비회원이 구매한 후 구매내역 조회
	OrderInfoDto unmemberOrder(OrderInfoBean bean);
	
	OrderInfoDto getOrderbyPass(String order_passwd);
}
