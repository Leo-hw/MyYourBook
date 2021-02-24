package pack.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.UserBean;
import pack.model.OrderInfoDto;
import pack.model.UserDto;
import pack.user.model.BuyInter;
import pack.user.model.BuyResultInter;
import pack.user.model.OldBookInter;
import pack.user.model.UserInter;

@Controller
public class BuyResultController {
	
	@Autowired
	private BuyResultInter buyResultInter;
	
	@Autowired
	private BuyInter buyInter;
	
	@Autowired
	private UserInter userInter;
	
	@Autowired
	private OldBookInter oldBookInter;
	
	
	@RequestMapping("buyresult")
	public ModelAndView result(HttpSession session, HttpServletRequest request, @RequestParam("radioPaytype") String radioPaytype) {
		ModelAndView modelAndView = new ModelAndView();
		OrderInfoDto dto = new OrderInfoDto();
		OrderInfoDto dto2 = new OrderInfoDto();
		String id = request.getParameter("order_id"); //아이디
		
		String order_person = request.getParameter("order_person"); //성명
		
		String ob_no = request.getParameter("ob_no");
		String user_passwd = request.getParameter("order_password");
		String user_address = request.getParameter("order_address");
		System.out.println(radioPaytype);
		
		if(radioPaytype.equals("무통장입금")) {
			radioPaytype = "0";
		}else {
			radioPaytype = "1";
		}
		String order_sum = request.getParameter("order_sum");
		System.out.println(radioPaytype);
		
		
		dto = buyResultInter.order(session, id, order_person, Integer.parseInt(order_sum), radioPaytype, user_passwd, user_address, ob_no);
		
		
		
		
		if(session.getAttribute("id") != null && request.getParameter("writepoint") != "") { // 회원인경우
			UserDto user = buyInter.point(id);
			UserBean bean = new UserBean();
			
			
			int point = Integer.parseInt(request.getParameter("point"));
			int writepoint = Integer.parseInt(request.getParameter("writepoint"));
			
			bean.setUser_id((String)session.getAttribute("id"));
			bean.setUser_point(writepoint);
			
			System.out.println(point + " " + writepoint);
			
			String name = (String)session.getAttribute("name");
			
			System.out.println(bean.getUser_point());
			
			
			userInter.usePoint(bean);
			
			UserDto user2 = userInter.selectUser(id);
			
			session.setAttribute("point", user2.getUser_point());
			
		}
		
		
		if(dto != null) {
			dto2 = buyInter.show(order_person);
			
			oldBookInter.update(Integer.parseInt(ob_no));
			
			
			
			modelAndView.addObject("buylist", dto2);
			
			modelAndView.setViewName("buyresult");
			return modelAndView;
		}else {
			modelAndView.setViewName("error");
			return modelAndView;
		}
	}
	
}
