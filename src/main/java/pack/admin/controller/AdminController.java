package pack.admin.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pack.admin.model.AdminInter;
import pack.controller.AdminBean;
import pack.model.AdminDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.RentInfoDto;

@Controller
public class AdminController {

	@Autowired
	AdminInter adminInter;

	@RequestMapping(value = "adminlogin", method = RequestMethod.GET)
	public String goLogin(HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "adminregister", method = RequestMethod.GET)
	public String goAdminSignup() {
		
		return "admin/adminsignup";
	}
	
	// 아이디 중복 여부 체크
		@RequestMapping(value = "checkSignupAdminId", method = RequestMethod.POST)
		public @ResponseBody String AjaxView(  
		        @RequestParam("admin_id") String admin_id){
			String str = "";
			System.out.println(admin_id);
			//AdminDto dto;
			if(adminInter.getAdminLoginInfo(admin_id) == null) {
				//dto = null;
				str = "YES";
				System.out.println(str);
			}else {
				//dto = adminInter.getAdminLoginInfo(admin_id);
				//System.out.println(dto.getAdmin_id());
				str = "NO";
			}
			
			
//			if(dto.getAdmin_id().equals(admin_id)){ //이미 존재하는 계정
//				str = "NO";	
//			}else{	//사용 가능한 계정
//				str = "YES";	
//			}
			return str;
		}
	
	@RequestMapping(value="adminsignupok", method = RequestMethod.POST)
	public String submit(AdminBean bean){

		boolean b = adminInter.insertAdmin(bean);
		if (b) {
			return "redirect:/adminlogin";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "idcheck", method = RequestMethod.GET)
	public ModelAndView goIdCheck(@RequestParam("id") String adminid) {
		ModelAndView view = new ModelAndView();
		
		System.out.println();
		System.out.println(adminid);
		
		String dto = adminInter.IdCheck(adminid);
		
		if(dto == null ||dto == "") {
			view.addObject("alert",adminid +"는 사용가능한 아이디입니다.");
		}else {
			view.addObject("alert",adminid +"는 존재하는 아이디입니다.");
		}
		view.setViewName("admin/adminidcheck");
		return view;
	}
	

	@RequestMapping(value = "admin_login", method = RequestMethod.POST)
	public String submitLogin(HttpSession session, 
			@RequestParam("admin_id") String admin_id,
			@RequestParam("admin_passwd") String admin_passwd,
			RedirectAttributes redirectAttr) {

		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);

		if (dto != null) {
			String retPasswd = dto.getAdmin_passwd();
			if (retPasswd.equals(admin_passwd)) {
				if(dto.getAdmin_acc() == 2) {
					session.setAttribute("admin_id", admin_id);
					return "redirect:/admin";
				}
			} 
		}
		return "admin/admin_login";
	}
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView list(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        
        String admin_id = (String)session.getAttribute("admin_id");
        
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        
        List<RentInfoDto> rulist = adminInter.getRentKing();
        List<OrderInfoDto> bulist = adminInter.getBuyKing();
        OrderInfoDto oprofit2 = adminInter.getObProfitmonth();
        OrderInfoDto nprofit2 = adminInter.getNbProfitmonth();
        OrderInfoDto rprofit2 = adminInter.getProfitmonth();
        List<NewBookDto> ocmonth = adminInter.getOcmonth();
        List<RentInfoDto> rentcmonth = adminInter.getRentcmonth();
        List<NewBookDto> msbook = adminInter.theMostSellBook();
        List<OldBookDto> mrbook = adminInter.theMostRentBook();
        view.addObject("bsb", msbook);
        view.addObject("brb", mrbook);
        view.addObject("rtm", rentcmonth);
		view.addObject("om",ocmonth);
		view.addObject("rp", rprofit2);
        view.addObject("ru", rulist);
        view.addObject("bu", bulist);
        view.addObject("op", oprofit2);
        view.addObject("np", nprofit2);
        view.setViewName("admin/adminmain");
        
        return view;
    }

	@RequestMapping(value = "admin_logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("admin_id");
		return "redirect:/adminlogin";
	}
	
	@RequestMapping(value = "gomain", method = RequestMethod.GET)
	public String adminlogout(HttpSession session) {
		session.removeAttribute("admin_id");
		return "redirect:/main";
	}
	
}
