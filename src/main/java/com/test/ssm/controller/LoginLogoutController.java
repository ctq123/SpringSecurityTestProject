package com.test.ssm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
public class LoginLogoutController {
	private Logger logger = Logger.getLogger(LoginLogoutController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value="error",required = false) boolean error,
			ModelMap model
			){
	
		logger.info("Received request to show login page");

		if(error){
			model.put("error", "invalid username or password!");
		}else{
			model.put("error","");
		}
		return "loginpage";
	}
	
	@RequestMapping(value="/denied",method=RequestMethod.GET)
	public String getDeniedPage(){
		logger.info("Receivd request to show denied page");
		return "deniedpage";
	}
	
	

}
