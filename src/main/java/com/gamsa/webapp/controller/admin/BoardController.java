package com.gamsa.webapp.controller.admin;



import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.entity.Notice;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	//객체는 ioc컨테이너에 있지!
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("notice")
	public String notice(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		
		model.addAttribute("list", noticeDao.getList(page, field, query));
		
		return "admin.board.notice.list";
	}
	
	@RequestMapping("notice/{id}")
	public String noticeDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
		
		//return "customer/notice-detail";
		return "admin.board.notice.detail";
	}
	
	@RequestMapping(value="notice/reg", method=RequestMethod.GET)
	public String noticeReg() {
		
		return "admin.board.notice.reg";
	}
	
	@RequestMapping(value="notice/reg", method=RequestMethod.POST)
	public String noticeReg(
			String title, 
			String content) throws UnsupportedEncodingException {
		
		//한글 깨질때
		//title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println(title);
		
		String writerId = "newlec";
		
		//noticeDao.insert(title, content, writerId);
		int row = noticeDao.insert(new Notice(title, content, writerId));
		
		return "redirect:../notice";
	}
}
