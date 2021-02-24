package pack.mypage.model;

import java.util.List;

import pack.model.OldBookDto;

public interface MydonorInter {
	//최근 기부 내역 3 추상메소드
	public List<OldBookDto> donorlistall();
	
	//최근 기부 내역 전체
	public List<OldBookDto> donorListbyId(String id);
}
