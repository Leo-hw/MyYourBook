package pack.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.model.AdminDto;

@Controller
public class BestRentbooksetController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="bestrentbookset", method=RequestMethod.GET)
	public ModelAndView goBestRentbookset(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
//	        List<OrderInfoDto> olist = adminInter.getDelayDeposit();
//	        view.addObject("delay", olist);
	        view.setViewName("admin/bestrentbookset");
		}
		
		return view;
	}
	
}
