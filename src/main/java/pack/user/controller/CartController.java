package pack.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.user.model.CartInter;

@Controller
public class CartController {
	@Autowired
	private CartInter cartInter;
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart() {
		//장바구니 기능 적용 전
		return "cart";
	}
	
	/*
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public ModelAndView cartList(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception{ //장바구니 조회
		ModelAndView modelAndView = new ModelAndView();
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		if(name == null && id == null) {
			modelAndView.setViewName("login");
			return modelAndView;
		}else {
			List<OldBookDto> list = cartInter.cartlist(id);
			System.out.println(id);
			modelAndView.addObject("list", list);
			modelAndView.setViewName("cart");
			return modelAndView;
		}

	}*/
	
	/*
	@RequestMapping(value = "cart", method = RequestMethod.POST)
	public ModelAndView cartadd() { //장바구니 추가 메소드
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping("deletelist")
	public String delete(@RequestParam("no") int no, @RequestParam("page") String page) {
		if(cartInter.delete(no) && page.equals("main")) { //장바구니 목록삭제
			return "redirect:/cart?page=main";
		}else if(cartInter.delete(no) && page.equals("rentmain")){
			return "redirect:/cart?page=rentmain";
		}else {
			return "error";
		}
	}
	*/
	
}
