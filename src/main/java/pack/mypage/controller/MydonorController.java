package pack.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.OldBookDto;
import pack.mypage.model.MydonorInter;

@Controller
public class MydonorController {
	
	@Autowired
	private MydonorInter myDonorInter;

	@RequestMapping("mydonor")
	public ModelAndView mydonorlist(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		String id = (String)session.getAttribute("id");
		//도서 기부 내역 모델앤뷰
		List<OldBookDto> donorlistall = myDonorInter.donorListbyId(id);
		modelAndView.setViewName("mypage/mydonor");
		modelAndView.addObject("dnabook", donorlistall);
		
		return modelAndView;
	}
}
