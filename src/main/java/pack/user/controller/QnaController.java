package pack.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaController {
	
	@RequestMapping("qna")
	public String qna() {
		return "qna";
	}
	
	@RequestMapping("qnawrite")
	public String qnawrite() {
		return "qnawrite";
	}
}
