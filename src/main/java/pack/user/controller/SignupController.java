package pack.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.controller.CardInfoBean;
import pack.controller.UserBean;
import pack.model.UserDto;
import pack.user.model.CardInfoInter;
import pack.user.model.UserInter;



@Controller
public class SignupController {
	@Autowired
	UserInter userinter;
	
	@Autowired
	CardInfoInter cardInter;
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String moveLogin() {
		return "signup";
	}
	
	// 아이디 중복 여부 체크
	@RequestMapping(value = "checkSignupId", method = RequestMethod.POST)
	public @ResponseBody String AjaxView(  
	        @RequestParam("id") String id){
		String str = "";
		UserDto dto;
		if(userinter.selectUser(id).equals(null)) {
			dto = null;
		}else {
			dto = userinter.selectUser(id);
		}
		
		
		System.out.println(id);
		System.out.println(dto.getUser_id());
		if(dto.getUser_id().equals(id)){ //이미 존재하는 계정
			str = "NO";	
		}else{	//사용 가능한 계정
			str = "YES";	
		}
		return str;
	}

	//회원가입 -> 유저 추가
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pwd1") String pwd,
			@RequestParam("name") String name, @RequestParam("rrnumber1") String rrnumber1,
			@RequestParam("rrnumber2") String rrnumber2, @RequestParam("phone1") String phone1, 
			@RequestParam("phone2") String phone2, @RequestParam("phone3") String phone3,
			@RequestParam("email1") String email1, @RequestParam("eamil2") String email2,
			@RequestParam("zipcode") String zipcode, @RequestParam("address1") String address1,
			@RequestParam("address2") String address2, @RequestParam("cardcomp") String cardcomp,
			@RequestParam("card1") String card1, @RequestParam("card2") String card2,
			@RequestParam("card3") String card3, @RequestParam("card4") String card4,
			@RequestParam("cardpwd") String cardpwd) {
		
		//유저 삽입
		UserBean userbean = new UserBean();
		userbean.setUser_id(id);
		userbean.setUser_passwd(pwd);
		userbean.setUser_name(name);
		userbean.setUser_tel(phone1+"-"+phone2+"-"+phone3);
		userbean.setUser_addr(address1 +" "+ address2);
		userbean.setUser_zip(zipcode);
		userbean.setUser_mail(email1+"@"+email2);
		// 대여횟수,포인트, 패널티 디폴트여서 삽입X
		userbean.setUser_birth(rrnumber1+"-"+rrnumber2);
		System.out.println(id +"\n"+pwd +"\n"+name +"\n"+phone1+phone2+phone3+"\n"+address1+address2+"\n"+zipcode+"\n"+email1+email2+"\n"+rrnumber1+rrnumber2);
		
		
		//카드 삽입
		CardInfoBean cardbean = new CardInfoBean();
		cardbean.setCard_ownerid(id);
		cardbean.setCard_owner(name);
		cardbean.setCard_comp(cardcomp);
		cardbean.setCard_no(card1+"-"+card2+"-"+card3+"-"+card4);
		cardbean.setCard_passwd(cardpwd);
		
		boolean buser = userinter.insertUser(userbean);
		boolean bcard = cardInter.insertCard(cardbean);
		if(buser && bcard) {
			return "redirect:/login";
		}else {
			return "redirect:/signup";
		}

	}
}


