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
import pack.controller.OldBookBean;
import pack.model.AdminDto;
import pack.model.OldBookDto;

@Controller
public class DonatebooklistController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="donatebooklist", method=RequestMethod.GET)
	public ModelAndView StandbyBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		
			List<OldBookDto> standbylist = adminInter.getStandby();
			view.setViewName("admin/standby");
			view.addObject("slist",standbylist);
		}
		
		return view;
	}
	
	@RequestMapping(value="standbyok", method=RequestMethod.POST)
	public String upState(OldBookBean bean, 
			@RequestParam(name="ob_no") int ob_no[], 
			@RequestParam(name="ob_state") String ob_state[],
			HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}

		boolean b = false;
		
		for (int i = 0; i < ob_no.length; i++) {
			bean.setOb_no(ob_no[i]);
			bean.setOb_state(ob_state[i]);
			b = adminInter.updateState(bean);
		}
		if(b) {
			System.out.println("성공");
			return "redirect:/donatebooklist";
		}else {
			return "redirect:/adminmain";
		}
	}
}
