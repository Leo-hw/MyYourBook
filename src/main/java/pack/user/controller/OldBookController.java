package pack.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.OldBookBean;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.OldBookImpl;
import pack.user.model.OldBookInter;
import pack.user.model.UserInter;

@Controller
public class OldBookController {
   
	   @Autowired
	   private OldBookInter oldBookInter;
	   
	   
	   @Autowired
	   UserInter userInter;
	   /*
	   @RequestMapping("oldbook")
	   public String oldbook(@RequestParam("book_no") String book_no) {
	      return "oldbook";
	   }*/
	   
	   @RequestMapping("oldbook")
	   public ModelAndView bookInfo(@RequestParam("book_no") String book_no) { //중고중에 1등급
	      ModelAndView modelAndView = new ModelAndView();
	      OldBookDto dto = oldBookInter.bookInfo(book_no);
	      boolean b = oldBookInter.readcnt(Integer.parseInt(book_no));
	      if(b) {
	         modelAndView.addObject("bookinfo", dto);
	         modelAndView.setViewName("oldbook");
	         return modelAndView;         
	      }else {
	         modelAndView.setViewName("error");
	         return modelAndView;
	      }
	   }
	   
	   @RequestMapping("oldrental")
	   public ModelAndView rentalInfo(@RequestParam("book_no") String book_no,
			   HttpSession session) {// 중고중에 2,3등급
		   
		  String user_id = (String)session.getAttribute("id");
		  
	      ModelAndView modelAndView = new ModelAndView();
	      OldBookDto dto = oldBookInter.rentalInfo(book_no);
	      boolean b = oldBookInter.readcnt(Integer.parseInt(book_no));
	      if(b) {
	         modelAndView.addObject("bookinfo", dto);
	         
	         UserDto rentUser = userInter.selectUser(user_id);
	         modelAndView.addObject("rentUser", rentUser);
	         
	         modelAndView.setViewName("oldrental");
	         
	         return modelAndView;         
	      }else {
	         modelAndView.setViewName("error");
	         return modelAndView;
	      }
	   }
	   
	   
	   
	   public String submit(OldBookBean bean) throws Exception {
	      
	      return "oldbook";
	   }
	   
	   
   
   
}