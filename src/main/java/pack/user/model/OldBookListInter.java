package pack.user.model;

import java.util.List;

import pack.model.OldBookDto;
import pack.model.UserDto;

public interface OldBookListInter {
	
	public List<OldBookDto> selectGenre(String ob_genre);
	public List<OldBookDto> selectGenre2(String ob_genre);
	List<OldBookDto> rentmain(); //중고 메인에 1등급 책
	List<OldBookDto> rentmain2(); //중고메인 2등급 책
	
	//1등급 전체조회
	List<OldBookDto> selectHighAll();
	
	//2,3등급 전체조회
	List<OldBookDto> selectLowAll();
	
	
	public OldBookDto bestOne();
	UserDto bestRead();
	
}
