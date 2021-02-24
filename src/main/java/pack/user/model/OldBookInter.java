package pack.user.model;

import pack.controller.OldBookBean;
import pack.model.OldBookDto;

public interface OldBookInter {
		OldBookDto getAllOldBook();
		boolean insertOldBook(OldBookBean bean);
		OldBookDto bookInfo(String book_no);
		OldBookDto rentalInfo(String book_no);
		boolean readcnt(int readcnt);
		OldBookDto view(String book_no);
	
		//대여했을 때 대여중으로 바꾸기
		boolean updateRentOldBook(String book_no);
		boolean update(int ob_no);
}
