package com.test.ssm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
	private Logger logger = Logger.getLogger(MainController.class);
	
	@RequestMapping(value="/common",method=RequestMethod.GET)
	public String getCommonPage(){
		logger.info("Received request to show common page");
		return "commonpage";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String getAdminPage(){
		logger.info("Received request to show admin page");
		return "adminpage";
	}
}
