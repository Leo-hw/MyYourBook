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
import pack.model.AdminDto;
import pack.model.OldBookDto;

@Controller
public class ReuseController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="reuse", method=RequestMethod.GET)
	public ModelAndView ReuseBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		
		List<OldBookDto> reuselist = adminInter.getReuse();
		view.setViewName("admin/reuse");
		view.addObject("reuselist",reuselist);
		}
		
		return view;
	}
	
	@RequestMapping(value="throwaway", method=RequestMethod.POST)
	public String ObThrow(@RequestParam(name="ob_no") int ob_no[], HttpSession session, ModelMap model) {

		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		boolean b = false;
		
		for (int i = 0; i < ob_no.length; i++) {
			b = adminInter.updateThrow(ob_no[i]);
		}
		if(b) {
			System.out.println("성공");
			return "redirect:/reuse";
		}else {
			return "redirect:/adminmain";
		}
		
	}
}
