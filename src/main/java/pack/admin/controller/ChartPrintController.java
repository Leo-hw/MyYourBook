package pack.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.admin.model.AdminInter;
import pack.controller.ChartPrintBean;
import pack.model.ChartPrintDto;

@Controller
public class ChartPrintController {
	Log logger = LogFactory.getLog(ChartPrintController.class);

	@Autowired
	private AdminInter adminInter;

	@RequestMapping(value = "chartprint", method = RequestMethod.POST)
	public String InsChartData(ChartPrintBean bean, @RequestParam(name = "cmonth") String cmonth[],
			@RequestParam(name = "obprofit") int obprofit[], @RequestParam(name = "nbprofit") int nbprofit[],
			@RequestParam(name = "rprofit") int rprofit[]) {

		boolean b = false;

		for (int i = 0; i < cmonth.length; i++) {
			bean.setCmonth(cmonth[i]);
			bean.setObprofit(obprofit[i]);
			bean.setNbprofit(nbprofit[i]);
			bean.setRprofit(rprofit[i]);
			if (adminInter.getChartmonth(cmonth[i]) == null || adminInter.getChartmonth(cmonth[i]) == "") {
				b = adminInter.insChartData(bean);
			} else {
				b = adminInter.upChartData(bean);
			}
		}

		if (b) {
			return "admin/monthprofit";

		} else {
			return "redirect:/admin";
		}

	}

	
	
	@RequestMapping(value = "showchart", method = RequestMethod.POST)
	@ResponseBody
	public void showChart() {
		logger.debug("ChartPrintController - showChart 실행");
		List<ChartPrintDto> charList = adminInter.getAllChar();
		
		for(ChartPrintDto c:charList) {
			
			JSONObject data = new JSONObject();
			JSONObject ajaxObjCols1 = new JSONObject();
			JSONObject ajaxObjCols2 = new JSONObject();
			JSONObject ajaxObjCols3 = new JSONObject();
			
			JSONArray ajaxArryCols = new JSONArray();
		
		ajaxObjCols1.put("cmonth", c.getCmonth());
		ajaxObjCols2.put("nbprofit", c.getNbprofit());	
		ajaxObjCols3.put("obprofit", c.getObprofit());
		
		ajaxArryCols.add(ajaxObjCols1);
		ajaxArryCols.add(ajaxObjCols2);	
		ajaxArryCols.add(ajaxObjCols3);
		System.out.println(data);
	}

	}
	
}
