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
import pack.controller.UserBean;
import pack.model.AdminDto;
import pack.model.OrderInfoDto;

@Controller
public class MonthBuyerController {
	
	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="monthbuyer", method=RequestMethod.GET)
	public ModelAndView goMonthBuyer(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
	        String cmonth = adminInter.getMonth();
	        List<OrderInfoDto> bulist = adminInter.getBuyKing();
	        view.addObject("cm", cmonth);
	        view.addObject("bu", bulist);
	        view.setViewName("admin/monthbuyer");
		}
		
		return view;
	}
	
	@RequestMapping(value = "givepoint4", method = RequestMethod.POST)
	public String JikwonUpJik(HttpSession session, ModelMap model, UserBean bean,
							@RequestParam(name="rn") int rank[], 
							@RequestParam(name="user_id") String userid[]){
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		
		boolean b = false;
		
		for (int i = 0; i < userid.length; i++) {
			if(userid[i] != null) {
				if(rank[i] == 1) {
					bean.setPluspoint(3000);
				}else if(rank[i] == 2) {
					bean.setPluspoint(2000);
				}else if(rank[i] == 3) {
					bean.setPluspoint(1000);
				}else {
					bean.setPluspoint(0);
				}
				bean.setUser_id(userid[i]);
				b = adminInter.upUserPoint(bean);
			}else b=true;
		}
		if(b) {
			System.out.println("성공");
			return "redirect:/monthbuyer";
		}else {
			return "redirect:/adminmain";
		}
	}
	
}
