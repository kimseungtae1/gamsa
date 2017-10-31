package com.gamsa.webapp.controller.admin;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.entity.Notice;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

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
		/*model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));*/
		
		//return "customer/notice-detail";
		return "admin.board.notice.detail";
	}
	
	@RequestMapping(value="notice/edit/{id}", method=RequestMethod.GET)
	public String noticeEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("n", noticeDao.get(id));
		return "admin.board.notice.edit";
	}
	
	@RequestMapping(value="notice/edit/{id}", method=RequestMethod.POST)
	public String noticeEdit(
			Notice notice, HttpServletRequest request
			) throws UnsupportedEncodingException {
		noticeDao.update(notice);

		return "redirect:../../notice";
	}
	
	
	@RequestMapping(value="notice/reg", method=RequestMethod.GET)
	public String noticeReg() {
		
		return "admin.board.notice.reg";
	}
	
	
	@RequestMapping(value="notice/reg", method=RequestMethod.POST)
	public String noticeReg(
			Notice notice, HttpServletRequest request
			) throws UnsupportedEncodingException {
		noticeDao.insert(notice);
		
		return "redirect:../notice";
	}
	
	@RequestMapping("notice/delete/{id}")
	public String noticeDelete(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("n", noticeDao.delete(id));
		
		return "redirect:../../notice";
	}
	
	
	
}





















