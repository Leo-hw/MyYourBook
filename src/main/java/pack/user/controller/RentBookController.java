package pack.user.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.RentInfoBean;
import pack.model.OldBookDto;
import pack.model.RentInfoDto;
import pack.model.UserDto;
import pack.user.model.OldBookInter;
import pack.user.model.RentInfoInter;
import pack.user.model.UserInter;

@Controller
public class RentBookController {
	@Autowired
	OldBookInter oldInter;
	
	@Autowired
	UserInter userInter;
	
	@Autowired
	RentInfoInter rentInter;
	
	@RequestMapping("rentbooklist")
	public String cart() {
		return "rentbooklist";
	}
	
	
	
	// 중고책 대여
	@RequestMapping(value = "Rental", method = RequestMethod.POST)
	public ModelAndView rentbook(HttpSession session, @RequestParam("ob_no") String rent_no) {
		//대여하기
		String user_id = (String)session.getAttribute("id");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String sdate = format.format(System.currentTimeMillis());
		
		
		//대여정보에 삽입
		RentInfoBean rentBean = new RentInfoBean();
		rentBean.setRent_id(user_id);
		rentBean.setRent_sdate(sdate);
		rentBean.setRent_no(Integer.parseInt(rent_no));//중고책 번호
		rentInter.rentOldBook(rentBean);
		
		// 중고책 대여 중으로 바꾸기
		oldInter.updateRentOldBook(rent_no);
		 	
		//유저 포인트 차감
		userInter.minusRentPoint(user_id);
		
		
		
		//대여한 책 정보
		ModelAndView view = new ModelAndView();
		
		OldBookDto rentBook = oldInter.rentalInfo(rent_no);
		
		System.out.println(rentBook.getOb_author());
		view.setViewName("rentbook");
		view.addObject("rentBook", rentBook);
		
		
		UserDto rentUser = userInter.selectUser(user_id);
		view.setViewName("rentbook");
		view.addObject("rentUser", rentUser);
		
		//방금 대여정보 가져오기
		RentInfoDto rentInfo = rentInter.getRentInfo(user_id);
		view.setViewName("rentbook");
		view.addObject("rentInfo", rentInfo);
		
		return view;
	}
	
	// 중고책 대여
	//@RequestMapping(value = "", method = RequestMethod.GET)
	public String rentbook() {
		return "";
	}
}
