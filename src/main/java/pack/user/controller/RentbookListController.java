package pack.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.OldBookInter;
import pack.user.model.OldBookListInter;

@Controller
public class RentbookListController {
	@Autowired
	private OldBookListInter oldBookListInter;
	
	@RequestMapping("rentlist1")
	public ModelAndView rentlist(@RequestParam("book") String book) {
		ModelAndView modelAndView = new ModelAndView();
		if (book.equals("a")) { // 미분류
			String ob_genre = "자기계발";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("b")) { // 소설류
			String ob_genre = "소설";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("c")) { // 에세이
			String ob_genre = "에세이";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("d")) { // 어린이
			String ob_genre = "어린이";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("e")) { // 유아
			String ob_genre = "유아";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			
			return modelAndView;
		} else if (book.equals("f")) { // 경제경영
			String ob_genre = "경제경영";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("g")) { // 인문학
			String ob_genre = "인문학";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("h")) { // 외국어
			String ob_genre = "외국어";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);

			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("i")) { // 사회과학
			String ob_genre = "사회과학";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("j")) { // 수험서,자격증
			String ob_genre = "자격증";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("k")) { // 대학교재
			String ob_genre = "대학교재";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		} else if (book.equals("l")) { // it 컴퓨터 모바일
			String ob_genre = "it";
			List<OldBookDto> list = oldBookListInter.selectGenre(ob_genre);
			modelAndView.addObject("oldbooklist", list);
			
			List<OldBookDto> list2 = oldBookListInter.selectGenre2(ob_genre);
			modelAndView.addObject("oldbooklow", list2);
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);

			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
			//oldRandom
		}else if(book.equals("rentmain")) {
			List<OldBookDto> list = oldBookListInter.rentmain();
			modelAndView.addObject("oldbooklist", list); //1등급 책 리스트
			
			List<OldBookDto> list2 = oldBookListInter.rentmain2();
			modelAndView.addObject("oldbooklow", list2); //2,3등급 책 리스트
			
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("rentmain");
			return modelAndView;
		}else if(book.equals("high")) {
			
			List<OldBookDto> list = oldBookListInter.selectHighAll();
			modelAndView.addObject("list", list); //1등급 책 리스트
			
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("alllist");
			return modelAndView;
		}else if(book.equals("low")) {
			List<OldBookDto> list = oldBookListInter.selectLowAll();
			modelAndView.addObject("list", list); //2,3등급 책 리스트
			
			
			OldBookDto best = oldBookListInter.bestOne();
			modelAndView.addObject("best", best);
			
			UserDto readbest = oldBookListInter.bestRead();
			modelAndView.addObject("readbest", readbest);
			
			modelAndView.setViewName("alllist");
			return modelAndView;
		}else {
			modelAndView.setViewName("error");
			return modelAndView;
		}

	}
}
