package com.gamsa.webapp.controller;

import javax.servlet.http.HttpServletRequest;

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

	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join() {
		return "member.join";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(
			Member member,
			HttpServletRequest request) {
		
		int result = memberDao.insert(member);
		
		return "index";
	}
	
	
	@RequestMapping(value="searchPw", method=RequestMethod.GET)
	public String searchPw() {
		return "member.searchPw";
	}
}
