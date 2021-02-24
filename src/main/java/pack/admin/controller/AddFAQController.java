package pack.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.controller.FaqBoardBean;
import pack.model.AdminDto;

@Controller
public class AddFAQController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="addfaq", method=RequestMethod.GET)
	public ModelAndView goAddfaq(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
	        view.setViewName("admin/addfaq");
		}
		
		return view;
	}
	
	@RequestMapping(value="faqadd", method=RequestMethod.POST)
	public String goFaqAdd(HttpSession session, ModelMap model, FaqBoardBean bean) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		boolean b = adminInter.insertFaqData(bean);
		if (b) {
			return "redirect:/addfaq";
		} else {
			return "error";
		}
		
	}
}
