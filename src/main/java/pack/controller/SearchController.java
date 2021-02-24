package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.NewBookDto;
import pack.model.SearchInter;

@Controller
public class SearchController {
	
	@Autowired
	private SearchInter searchInter;
	
	@RequestMapping("search")
	public ModelAndView DataAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		
		ArrayList<NewBookDto> list = searchInter.getDataAll(type, search);
		modelAndView.setViewName("list");
		modelAndView.addObject("newbooklist", list);
		
		return modelAndView;
	}
	
}
