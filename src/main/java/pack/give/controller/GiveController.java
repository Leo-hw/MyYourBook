package pack.give.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
//import javax.activation.CommandMap;

import pack.give.common.CommandMap;
import pack.give.service.GiveService;
import pack.model.AdminDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.UserInter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pack.controller.OldBookBean;

@Controller
@SessionAttributes("user")
public class GiveController {
	Log log = LogFactory.getLog(GiveController.class);

	@Resource(name = "giveService")
	private GiveService giveService;


	@RequestMapping(value = "give", method = RequestMethod.GET)
	public String give() { // 기증메소드
		return "give";
	}

	@RequestMapping(value = "give", method = RequestMethod.POST)
	public ModelAndView donate(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception {
		//commandMap 사용
		ModelAndView mv = new ModelAndView("giveresult");
		//세션으로 id와 name 값을 받아서, db에 입력.
		String user_id = (String) session.getAttribute("id");
		String user_name = (String) session.getAttribute("name");
		commandMap.put("ob_userid", user_id);
		commandMap.put("ob_donor", user_name);
	
		giveService.insertOldBook(commandMap.getMap(), request);
	

		if (commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;

			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
		mv.addObject(user_id);

		return mv;
	}
	
	

}
