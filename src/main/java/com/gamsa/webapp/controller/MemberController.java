package com.gamsa.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gamsa.webapp.dao.MemberDao;
import com.gamsa.webapp.entity.Member;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "member.login";
	}
	/*
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
			String id,
			String pwd) {
		
		memberDao.get(id);
		
		return "redirect:../index";
	}*/
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join() {
		return "member.join";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(
			Member member) {
		
		memberDao.insert(member);
		
		return "redirect:login";
	}
	
	
	@RequestMapping(value="searchPw", method=RequestMethod.GET)
	public String searchPw() {
		return "member.searchPw";
	}
}
