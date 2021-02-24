package pack.user.model;

import java.util.List;

import pack.controller.NewBookBean;
import pack.model.NewBookDto;


public interface NewBookInter {
	
	NewBookDto getBestSeller();
	List<NewBookDto> getBookAll();
	List<NewBookDto> getGenre(String nb_genre);
	List<NewBookDto> getBest();
	List<NewBookDto> getNew();
	NewBookDto selectNewbook(int nb_no);
	List<NewBookDto> selectAuthorList(String nb_authour);
	
	//새책 조회수 올리기
	boolean plusReadCnt(int nb_no);

	
	
	
}
