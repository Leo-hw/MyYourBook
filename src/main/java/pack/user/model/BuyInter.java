package pack.user.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.UserDto;

public interface BuyInter {
	
	UserDto point(String user_id);
	OrderInfoDto show(String order_person);
}
