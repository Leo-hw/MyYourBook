package pack.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.CardInfoDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.UserDto;
import pack.user.model.BuyInter;
import pack.user.model.CardInfoInter;
import pack.user.model.OldBookInter;
import pack.user.model.UserInter;

@Controller
public class BuyController {
	@Autowired
	private OldBookInter oldBookInter;
	
	@Autowired
	private BuyInter buyInter;
	
	@Autowired
	private CardInfoInter cardInfoInter;
	
	@RequestMapping("buy")
	public ModelAndView buy(@RequestParam("ob_no") String ob_no, HttpSession session) {
		String user_id = (String)session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		OldBookDto dto = oldBookInter.bookInfo(ob_no);
		modelAndView.addObject("buyinfo", dto);
		//System.out.println("buyController : buyinfo 추가");
		UserDto user = buyInter.point(user_id);
		modelAndView.addObject("point", user);

		//System.out.println("buyController : point 추가");
		CardInfoDto card = cardInfoInter.selectCard(user_id);
		modelAndView.addObject("card", card);

		//System.out.println("buyController : card 추가");
		modelAndView.setViewName("buy");
		return modelAndView;
	}
	
	
	
	
}
