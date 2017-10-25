package com.gamsa.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login1() {
		return "member.login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login2() {
		return "member.login";
	}
	
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join() {
		return "member.join";
	}
	
	
	@RequestMapping(value="searchPw", method=RequestMethod.GET)
	public String searchPw() {
		return "member.searchPw";
	}
}
