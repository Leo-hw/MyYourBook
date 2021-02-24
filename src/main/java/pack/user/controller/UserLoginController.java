	package pack.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.UserDto;
import pack.user.model.UserInter;

@Controller
public class UserLoginController {
	@Autowired
	private UserInter userinter;
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String moveLogin() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pwd") String pwd,
						HttpSession session) {
		
		System.out.println(id);
		UserDto userdto = userinter.selectUser(id);
		
		//아이디가 없을 경우
		if(userdto == null) {
			return "error";
		}
		
		
		if(userdto.getUser_id().equals(id) && userdto.getUser_passwd().equals(pwd)) {
			//페이지에 뿌릴 내용
			session.setAttribute("id", id);
			session.setAttribute("name", userdto.getUser_name());
			session.setAttribute("point", userdto.getUser_point());
			return "redirect:/buymain";
		}else {
			return "redirect:/login";
		}
	}
	
	
	//로그 아웃!
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/buymain";
	}
	
	
	
	
	
}
