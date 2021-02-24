package pack.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RentMainController {
	
	
	@RequestMapping("rentmain1")
	public String moveRentmain() {
		return "rentmain";
	}
	
	@RequestMapping("rentmain")
	public String main(@RequestParam("book") String book) {
		
		//여기도 컨트롤러에 컨트롤러 물려서 rentlist.jsp로 받아서 주자
		if(book.equals("a")) {
			//System.out.println("a");
			return "redirect:/rentlist1?book=a";
		}else if(book.equals("b")){
			//System.out.println("b");
			return "redirect:/rentlist1?book=b";
		}else if(book.equals("c")){
			//System.out.println("c");
			return "redirect:/rentlist1?book=c";
		}else if(book.equals("d")){
			//System.out.println("d");
			return "redirect:/rentlist1?book=d";
		}else if(book.equals("e")){
			//System.out.println("e");
			return "redirect:/rentlist1?book=e";
		}else if(book.equals("f")){
			//System.out.println("f");
			return "redirect:/rentlist1?book=f";
		}else if(book.equals("g")){
			//System.out.println("g");
			return "redirect:/rentlist1?book=g";
		}else if(book.equals("h")){
			//System.out.println("h");
			return "redirect:/rentlist1?book=h";
		}else if(book.equals("i")){
			//System.out.println("i");
			return "redirect:/rentlist1?book=i";
		}else if(book.equals("j")){
			//System.out.println("j");
			return "redirect:/rentlist1?book=j";
		}else if(book.equals("k")){
			//System.out.println("k");
			return "redirect:/rentlist1?book=k";
		}else if(book.equals("l")){
			//System.out.println("l");
			return "redirect:/rentlist1?book=l";
		}else if(book.equals("main")){
			//System.out.println("성공");
			return "redirect:/rentlist1?book=main";
		}else if(book.equals("rentmain")) {
			
			return "redirect:/rentlist1?book=rentmain";
		}else if(book.equals("high")) {
			
			return "redirect:/rentlist1?book=high";
		}else if(book.equals("low")) {
			
			return "redirect:/rentlist1?book=low";
		}else {
			return "error";
		}
	}
}
