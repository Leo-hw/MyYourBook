package pack.user.model;

import java.util.List;

import pack.model.NewBookDto;

public interface NewBookListInter {
	List<NewBookDto> getBestSeller();
	
	//추가
	public List<NewBookDto> selectReadTop3();
		
	public List<NewBookDto> selectRandom10();
		
	public List<NewBookDto> selectBest30();
		
	public List<NewBookDto> selectGenre(String genre);
		
	public List<NewBookDto> selectNew();
		
	public NewBookDto selectBest();
}
