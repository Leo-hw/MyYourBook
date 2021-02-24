package pack.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pack.admin.model.AdminInter;
import pack.controller.NewBookBean;
import pack.model.AdminDto;

@Controller
public class BookRegisterController {


	@Autowired
	AdminInter adminInter;

	@RequestMapping(value = "bookregister", method = RequestMethod.GET)
	public String insert(HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		return "admin/bookregister";
	}

	@RequestMapping(value = "bookregister", method = RequestMethod.POST)
	public String submit(NewBookBean bean, HttpSession session, ModelMap model, @RequestParam("nb_image") String nb_image) throws Exception {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		else {
			AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);
		}
		if(nb_image.equals("") | nb_image == null) {
			bean.setNb_image("resources/images/notready.jpg");
		}
		
		boolean b = adminInter.insertBookData(bean);
		if (b) {
			return "redirect:/bookregister";
		} else {
			return "error";
		}
	}

}
