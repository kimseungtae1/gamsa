package com.gamsa.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/*")
//@ResponseBody
public class HomeController {
	
	@RequestMapping("index")
	public String index() {
		
		return "home.index";
		//return "/WEB-INF/views/index.jsp";
	}
	
}
