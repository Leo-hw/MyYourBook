package pack.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.controller.AdminBean;
import pack.model.AdminDto;

@Controller
public class AdminInfoController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value = "admininfo", method = RequestMethod.GET)
	public ModelAndView adminData(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			modelAndView.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
			modelAndView.setViewName("admin/admininfo");
			modelAndView.addObject("info", dto);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="updateadmin", method = RequestMethod.POST)
	public String updateAdmin(AdminBean bean) {
		boolean b = adminInter.updateAdmin(bean);
		if (b) {
			return "redirect:/admininfo";
		} else {
			return "error";
		}
	}
	
}
