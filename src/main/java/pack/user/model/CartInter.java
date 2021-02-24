package pack.user.model;

import java.util.List;

import pack.model.OldBookDto;
import pack.model.OrderInfoDto;

public interface CartInter {
	List<OldBookDto> cartlist(String user_id);
	boolean delete(int order_no);
	
}
