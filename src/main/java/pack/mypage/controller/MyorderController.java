package pack.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.NewBookBean;
import pack.model.NewBookDto;
import pack.model.OrderInfoDto;
import pack.mypage.model.MyorderInter;

@Controller
public class MyorderController {

	@Autowired
	private MyorderInter myOrderInter;

	@RequestMapping("myorder")
	public ModelAndView myorderlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		// 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> orderlistall = myOrderInter.orderlistall(user_id);
		modelAndView.setViewName("mypage/myorder");
		modelAndView.addObject("odbook", orderlistall);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myOrderInter.recommandNewBook();
		modelAndView.setViewName("mypage/myorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("myoldorder")
	public ModelAndView myorderoldlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		String booktype = "중고책";
		// 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> orderoldlistall = myOrderInter.orderoldlistall(user_id);
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("odbook", orderoldlistall);
		modelAndView.addObject("booktype", booktype);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myOrderInter.recommandNewBook();
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("myneworder")
	public ModelAndView myneworderlist(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		String booktype = "새책";
		// 새책 전체 주문 내역 모델앤뷰
		List<OrderInfoDto> ordernewlistall = myOrderInter.ordernewlistall(user_id);
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("odbook", ordernewlistall);
		modelAndView.addObject("booktype", booktype);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myOrderInter.recommandNewBook();
		modelAndView.setViewName("mypage/mytypeorder");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}

	@RequestMapping("orderinfocheck")
	public ModelAndView myorderinfoAll(HttpSession session, @RequestParam("no") String orderlist_no) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();

		// 주문번호별 주문확인 모델앤뷰
		OrderInfoDto myorderinfo = myOrderInter.myorderinfo(orderlist_no);
		List<OrderInfoDto> myorderinfoall = myOrderInter.myorderinfoall(orderlist_no);
		modelAndView.setViewName("mypage/orderinfocheck");
		modelAndView.addObject("odinfo", myorderinfo);
		modelAndView.addObject("odinfoall", myorderinfoall);

		// 랜덤 새책 추천
		NewBookDto randNewbook = myOrderInter.recommandNewBook();
		modelAndView.setViewName("mypage/orderinfocheck");
		modelAndView.addObject("randNewbook", randNewbook);

		return modelAndView;
	}
	
	@RequestMapping("deletemyorder")
	public ModelAndView deletemyorder(HttpSession session, NewBookBean bean, @RequestParam("no") int order_no, @RequestParam("count") int order_scount, @RequestParam("bookno") int nb_no) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		
		//주문 내역 삭제
		boolean b = myOrderInter.deletemyorder(order_no);
		if(b) {
			bean.setNb_scount(order_scount);
			bean.setNb_no(nb_no);
			boolean c = myOrderInter.upNbScount(bean);
			if(c)
			modelAndView.setViewName("redirect:/myneworder");
		}else {
			modelAndView.setViewName("error");
		}


		return modelAndView;
	}
	
}
