package pack.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewbooklistController {
	
   @RequestMapping("newbooklist")
   public String main(@RequestParam("book") String book) {
      
      if(book.equals("a")) { //미분류
         return "redirect:/list?book=a";
         
      }else if(book.equals("b")){ //소설류
         return "redirect:/list?book=b";
         
      }else if(book.equals("c")){ // 에세이
         return "redirect:/list?book=c";
         
      }else if(book.equals("d")){ // 어린이
         return "redirect:/list?book=d";
         
      }else if(book.equals("e")){ // 유아
         return "redirect:/list?book=e";
         
      }else if(book.equals("f")){ //경제경영
         return "redirect:/list?book=f";
         
      }else if(book.equals("g")){ // 인문학
         return "redirect:/list?book=g";
         
      }else if(book.equals("h")){ // 외국어
         return "redirect:/list?book=h";
         
      }else if(book.equals("i")){ // 사회과학
         return "redirect:/list?book=i";
         
      }else if(book.equals("j")){ // 수험서
         return "redirect:/list?book=j";
         
      }else if(book.equals("k")){ // 대학교재
         return "redirect:/list?book=k";
         
      }else if(book.equals("l")){ //it 컴퓨터 모바일
         return "redirect:/list?book=l";
         
      }else if(book.equals("best")){ //베스트 30
         return "redirect:/list?book=best";
         
      }else if(book.equals("new")){ //신간
         return "redirect:/list?book=new";
         
      }else if(book.equals("all")){ //전체보기
    	 return "redirect:/list?book=all";
    	 
      }else  {
    	  return "error";
      }
   }
}