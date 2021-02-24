package pack.give.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



@Component("fileUtils")
public class FileUtils{
	private static final String uploadPath= "C:\\work\\wsou\\team3project\\src\\main\\webapp\\resources\\upload\\";
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/*
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return definition.getBeanClassName();
	}
	*/
	
	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String originalFileName = null;
    	String originalFileExtension = null;
    	String storedFileName = null;
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> listMap= null;
    	
		String ob_no = String.valueOf(map.get("ob_no"));
        String ob_userid = String.valueOf(map.get("ob_userid"));
        
        File file  = new File(uploadPath);
            if(file.exists() ==false) {
            	file.mkdirs();
        }
        while(iterator.hasNext()) {
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false) {
        		originalFileName = multipartFile.getOriginalFilename();
        		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        		storedFileName = FileUtils.getRandomString() +originalFileExtension;
        		//System.out.println(originalFileName);
        		log.debug("-----------------------------------------");
        		log.debug("original_file_name" + originalFileName);
        		log.debug("file_size" + multipartFile.getSize());
        		log.debug("------------------------------------------");
        		
        		file = new File(uploadPath+storedFileName);
        		multipartFile.transferTo(file);
        		
        		listMap = new HashMap<String, Object>();
        		listMap.put("ob_no", ob_no);
        		listMap.put("original_file_name", originalFileName);
        		listMap.put("stored_file_name", storedFileName);
        		listMap.put("file_size", multipartFile.getSize());
        		listMap.put("ob_userid", ob_userid);
        		list.add(listMap);
        		//System.out.println(listMap.get("originalFileName"));
        	}
        }
        return list;
    }
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

