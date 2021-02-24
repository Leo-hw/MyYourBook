package pack.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.InqueryBean;
import pack.model.InqueryDto;
import pack.model.UserDto;
import pack.user.model.SosInter;
import pack.user.model.UserInter;

@Controller
public class SosController {

	@Autowired
	SosInter sosinter;

	@Autowired
	UserInter userInter;

	@RequestMapping("sos") // 1:1 문의 버튼 눌렀을때 public String
	public ModelAndView sos(HttpSession session) {
		String inq_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		
		if(inq_id == null | inq_id=="") modelAndView.setViewName("login");
		
		System.out.println("찍히나?");

		List<InqueryDto> inqList = sosinter.inqlist(inq_id);
		modelAndView.setViewName("sos");
		modelAndView.addObject("inqinfo",inqList);
		return modelAndView;
	}
		
	
	@RequestMapping(value = "sospage", method = RequestMethod.GET) // 문의 안에서 문의하기 버튼 눌렀을때
	public String sospage() {
				return "sospage";
		}
	
	@RequestMapping(value = "sospage", method = RequestMethod.POST)
	public String submit(InqueryBean bean, HttpSession session, ModelMap model)
			throws Exception {
		String id = (String) session.getAttribute("id");
		if (id == null | id == "") {
			return "login";
		} else {
			UserDto dto = userInter.selectUser(id);
			model.addAttribute("inqinfo", dto);
		}
		bean.setInq_id(id);
		boolean b = sosinter.insertInquery(bean);
		if (b) {
			return "redirect:/sos";
		} else {
			return "error";
		}
	}

	/*
	@RequestMapping("sos") // 1:1 문의 버튼 눌렀을때 public String
	public String sos(@RequestParam("page") String a, Model model) {

		if (a.equals("main")) { // 구매메인일때 1:1문의 돌아가는것 model.addAttribute("page", a);
			return "sos";
		} else { // 중고대여 메인일때 1:1문의 돌아가는것 model.addAttribute("page", a);
			return "sos";
		}
	}
	*/
}
