package pack.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.controller.NewBookBean;
import pack.model.AdminDto;
import pack.model.OrderInfoDto;

@Controller
public class OrderCancelController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="ordercancel", method=RequestMethod.GET)
	public ModelAndView delayDeposit(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
	        List<OrderInfoDto> olist = adminInter.getDelayDeposit();
	        view.addObject("delay", olist);
	        view.setViewName("admin/delaydeposit");
		}
		
		return view;
	}
	
	@RequestMapping(value="delorder", method=RequestMethod.POST)
	public String DelUser(HttpSession session, ModelMap model, NewBookBean bean,
						@RequestParam(name="orderlist_no") String orderlist_no[],
						@RequestParam(name="order_bookno") int order_bookno[],
						@RequestParam(name="order_scount") int order_scount[]) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		boolean b = false;
		boolean u = false;
		
		for (int i = 0; i < orderlist_no.length; i++) {
			b = adminInter.delOrder(orderlist_no[i]);
			bean.setNb_no(order_bookno[i]);
			bean.setNb_stock(order_scount[i]);
			u = adminInter.RollbackStock(bean);
		}
		if(b) {
			if(u)
			return "redirect:/ordercancel";
		}
		return "redirect:/admin";
	}
}
