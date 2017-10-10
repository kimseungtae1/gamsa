package com.gamsa.webapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gamsa.webapp.dao.NoticeDao;

@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao; // 다오 이용하겠다!
	
	@RequestMapping("notice")
	public String notice(
		@RequestParam(value="p", defaultValue="1") Integer page,
		@RequestParam(value="f", defaultValue="title") String field, //title을 기본값으로 검색하겠다
		@RequestParam(value="q", defaultValue="") String query,
		Model model) {

		model.addAttribute("list", noticeDao.getList(page, field, query));
		
		/*
		String output = String.format("p:%s, q:%s\n", page, query);
		output += String.format("title : %s\n", list.get(0).getTitle());*/
		
		//return "customer/notice"; //servlet-context.xml에서 설정을 resolve 라이브러리를 이용해서 설정하였기때문에 줄여쓸수있다!
		return "customer.notice.list"; //tiles를 쓰기위해서 tiles 설정파일에 지정한 대로 바꿔준다. 대신 tiles resolver를 설치해줘야 한다.
	}

	@RequestMapping("notice/{id}")
	public String noticeDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
		
		//return "customer/notice-detail";
		return "customer.notice.detail";
	}
}
