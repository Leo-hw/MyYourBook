package pack.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.NewBookDto;
import pack.user.model.NewBookInter;

@Service
@Controller
public class ListController {
	@Autowired
	private NewBookInter newBookInter;

	@RequestMapping("list")
	public ModelAndView list(@RequestParam("book") String book) {
		ModelAndView modelAndView = new ModelAndView();
		if (book.equals("a")) { // 미분류
			String nb_genre = "성공학";
			List<NewBookDto> list = newBookInter.getGenre(nb_genre);
			modelAndView.setViewName("list");
			modelAndView.addObject("newbooklist", list);
			return modelAndView;
			
		} else if (book.equals("b")) { // 소설
			 String nb_genre = "소설";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
	    	  
		} else if (book.equals("c")) { // 에세이
			 String nb_genre = "에세이";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  //System.out.println(list);
	    	  return modelAndView;
	    	  
		} else if (book.equals("d")) { // 어린이
			 String nb_genre = "어린이";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("e")) { // 유아
			 String nb_genre = "유아";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("f")) { // 경제경영
			 String nb_genre = "경제경영";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("g")) { // 인문학
			 String nb_genre = "인문학";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("h")) { // 외국어
			 String nb_genre = "외국어";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("i")) { // 사회과학
			 String nb_genre = "사회과학";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("j")) { // 수험서
			 String nb_genre = "수험서";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("k")) { // 대학교재
			 String nb_genre = "대학교재";
	    	  List<NewBookDto> list = newBookInter.getGenre(nb_genre);
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("l")) { // it 컴퓨터/모바일
			String nb_genre = "it";
			List<NewBookDto> list = newBookInter.getGenre(nb_genre);
			modelAndView.setViewName("list");
			modelAndView.addObject("newbooklist", list);
			return modelAndView;
		} else if (book.equals("best")) { //베스트30
	    	  List<NewBookDto> list = newBookInter.getBest();
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("new")) { //신간
	    	  List<NewBookDto> list = newBookInter.getNew();
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else if (book.equals("all")) { //전체목록
	    	  List<NewBookDto> list = newBookInter.getBookAll();
	    	  modelAndView.setViewName("list");
	    	  modelAndView.addObject("newbooklist", list);
	    	  return modelAndView;
		} else {
			modelAndView.setViewName("error");
			return modelAndView;
		}
	}

}
