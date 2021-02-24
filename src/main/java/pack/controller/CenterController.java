package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.FaqBoardDto;
import pack.model.FaqDao;

@Controller
public class CenterController {

	@Autowired
	private FaqDao faqDao;
	

	@RequestMapping(value = "center")
	public String centerC() {
		//System.out.println();
		//System.out.println("넘어갔어");
		//System.out.println(); 
		return "center";
	}

	
	@RequestMapping("qnaAll")
	@ResponseBody
	public Map<String, Object> qnaAll() {
		
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;
		List<FaqBoardDto> qnaList = faqDao.qnaListAll();
		
		for(FaqBoardDto q:qnaList) {
			data = new HashMap<String, String>();
			data.put("faq_no", Integer.toString(q.getFaq_no()));
			data.put("faq_date", q.getFaq_date());
			data.put("faq_title", q.getFaq_title());
			data.put("faq_cont", q.getFaq_content());
			data.put("faq_type", q.getFaq_type());
			System.out.println("들어갔나?" + q.getFaq_date());
			dataList.add(data);
		}
		//System.out.println(dataList);
		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}
	
	@RequestMapping("faqDetail")
	@ResponseBody
	public Map<String, Object> faqDetail(@RequestParam("no") String faq_no){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;
		List<FaqBoardDto> faqList = faqDao.faqDetail(faq_no);
		
		for(FaqBoardDto f:faqList) {
			data = new HashMap<String, String>();
			data.put("faq_no", faq_no);
			data.put("faq_title", f.getFaq_title());
			data.put("faq_content", f.getFaq_content());
			data.put("faq_date", f.getFaq_date());
			data.put("faq_type", f.getFaq_type());
			//System.out.println("3");
			dataList.add(data);
		}
		Map<String, Object> faqDatas = new HashMap<String, Object>();
		faqDatas.put("datas", dataList);
		return faqDatas;
	}
	
	@RequestMapping("qnaOrder")
	@ResponseBody
	public Map<String, Object> abc(@RequestParam("faq_type") String faq_type){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = null;
		List<FaqBoardDto> qnaList = faqDao.qnaListPart(faq_type);
		
		for(FaqBoardDto q:qnaList) {
			data = new HashMap<String, String>();
			data.put("faq_no", Integer.toString(q.getFaq_no()));
			data.put("faq_title", q.getFaq_title());
			data.put("faq_content", q.getFaq_content());
			data.put("faq_date", q.getFaq_date());
			data.put("faq_type", q.getFaq_type());
			dataList.add(data);
		}
		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}
	
	@RequestMapping("centerpage")
	public String centerPage(@RequestParam("page") String a, Model model) {
		
		if(a.equals("order")) {
			model.addAttribute("page",a);
			return "centerOrder";
		}else if(a.equals("deliver")) {
			model.addAttribute("page",a);
			return "centerDeliver";
		}else if(a.equals("product")) {
			model.addAttribute("page",a);
			return "centerProduct";
		}else {
			return "qnaAll";
		}
	}
	
	
}
