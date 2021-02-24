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
import pack.model.RentInfoDto;
import pack.model.UserDto;

@Controller
public class UserpenaltyController {

	@Autowired
	AdminInter adminInter;
	
	@RequestMapping(value="userpenalty", method=RequestMethod.GET)
	public ModelAndView goLongterm(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			view.setViewName("admin/admin_login");
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
	        List<RentInfoDto> rlist = adminInter.getDelayData();
	        view.addObject("longd", rlist);
	        view.setViewName("admin/longdelay");
		}
		
		return view;
	}
	
	@RequestMapping(value="delay", method=RequestMethod.POST)
	public String goDelayCount(HttpSession session, ModelMap model, UserBean ubean,
								@RequestParam(name="rent_no") int rent_no[],
								@RequestParam(name="user_id") String user_id[],
								@RequestParam(name="delpoint") int delpoint[]) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        
		}
		if(rent_no.length == 0) {
			return "redirect:/delaycount";
		}
		boolean b = false;
		boolean o = false;
		
		for (int i = 0; i < rent_no.length; i++) {
			ubean.setUser_id(user_id[i]);
			ubean.setDelpoint(delpoint[i]);
			b = adminInter.updateUser(ubean);
			if(b) {
				System.out.println("회원 업데이트 성공");
				o = adminInter.removeOb(rent_no[i]);
			}	
		}
		if(o) {
			return "redirect:/delaycount";
		}
		
		return "redirect:/admin";	
	}
	
	@RequestMapping(value="delaycount", method=RequestMethod.GET)
	public String goDelay(HttpSession session, ModelMap model) {
		
		List<String> rent_id = adminInter.getDelayId();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		boolean b = false;
		
		for (int i = 0; i < rent_id.size(); i++) {
			String rentid = rent_id.get(i);
			b = adminInter.updateDcount(rentid);
		}
		if(b) {
			List<UserDto> dlist = adminInter.getDelay();
			model.addAttribute("dinfo", dlist);
			return "admin/delayinfo";
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value="refusebook", method=RequestMethod.POST)
	public String goRefuse(HttpSession session, ModelMap model, UserBean bean,
						@RequestParam(name="user_id") String user_id[],
						@RequestParam(name="user_penalty") String user_penalty[]) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		boolean b = false;
		
		for (int i = 0; i < user_id.length; i++) {
			bean.setUser_id(user_id[i]);
			bean.setUser_penalty(user_penalty[i]);
			b = adminInter.updatePenalty(bean);
		}
		if(b) {
			model.addAttribute("rflist", adminInter.getRefuse());
			return "admin/refuse";
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value="refusebook", method=RequestMethod.GET)
	public String goRefuse(HttpSession session, ModelMap model) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        model.addAttribute("rflist", adminInter.getRefuse());
			return "admin/refuse";
		}
	}
	
	@RequestMapping(value="userpenaltycheck", method=RequestMethod.GET)
	public String penaltyCheck(HttpSession session, ModelMap model) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
	        model.addAttribute("dulist", adminInter.getUserdel());
	        model.addAttribute("ulist", adminInter.getUsercheck());
			return "admin/penaltycheck";
		}
	}
	
	@RequestMapping(value="deluser", method=RequestMethod.POST)
	public String DelUser(HttpSession session, ModelMap model,
						@RequestParam(name="user_id") String user_id[]) {
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		
		boolean b = false;
		
		for (int i = 0; i < user_id.length; i++) {
			b = adminInter.updateDelUser(user_id[i]);
		}
		if(b) {
			return "redirect:/userpenaltycheck";
		}
		return "redirect:/admin";
	}
}
