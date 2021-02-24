package pack.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.CardInfoDto;
import pack.model.InqueryDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.mypage.model.MypageInter;
import pack.user.model.NewBookListInter;

@Controller
public class MypageController {

	@Autowired
	private MypageInter myPageInter;

	@RequestMapping("mypage")
	public ModelAndView list3(HttpSession session) {
		String user_id =(String)session.getAttribute("id");
		
		ModelAndView modelAndView=new ModelAndView();
		
		
		//최근 주문 내역 3 모델앤뷰
		List<OrderInfoDto> orderlist = myPageInter.orderlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("odbook", orderlist);
		
		//최근 대여 내역 3 모델앤뷰
		List<OldBookDto> rentlist = myPageInter.rentlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("rtbook", rentlist);
		
		//최근 도서 기부 내역 3 모델앤뷰
		List<OldBookDto> donorlist = myPageInter.donorlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("dnbook", donorlist);
		
		//랜덤 새책 추천
		NewBookDto randNewbook = myPageInter.recommandNewBook();
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("randNewbook", randNewbook);
		
		
		//최근 카드정보 내역 3 모델앤뷰
		List<CardInfoDto> cardlist = myPageInter.cardlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("cdinfo", cardlist);
		
		//최근 문의 내역 3 모델앤뷰
		List<InqueryDto> inqlist = myPageInter.inqlist(user_id);
		modelAndView.setViewName("mypage/mypage");
		modelAndView.addObject("inqinfo", inqlist);
	 
		return modelAndView;
	}
}