package pack.user.model;

import javax.servlet.http.HttpSession;

import pack.model.OrderInfoDto;

public interface BuyResultInter {
	public OrderInfoDto order(HttpSession session, String user_id, String user_name, int view, String a, String passwd, String address, String ob_no);
	OrderInfoDto view(HttpSession session, String user_passwd);
}
