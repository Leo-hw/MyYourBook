package pack.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pack.admin.model.AdminInter;
import pack.model.AdminDto;
import pack.model.OldBookDto;

@Controller
public class SellOblistController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="selloblist", method=RequestMethod.GET)
	public ModelAndView goUser(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			modelAndView.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);

		List<OldBookDto> selloblist = adminInter.getSellOb();
		
		modelAndView.setViewName("admin/sellobinfo");
		modelAndView.addObject("soblist", selloblist);
		}
		
		return modelAndView;
	}
	
}
