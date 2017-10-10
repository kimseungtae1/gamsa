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
	private NoticeDao noticeDao; // �ٿ� �̿��ϰڴ�!
	
	@RequestMapping("notice")
	public String notice(
		@RequestParam(value="p", defaultValue="1") Integer page,
		@RequestParam(value="f", defaultValue="title") String field, //title�� �⺻������ �˻��ϰڴ�
		@RequestParam(value="q", defaultValue="") String query,
		Model model) {

		model.addAttribute("list", noticeDao.getList(page, field, query));
		
		/*
		String output = String.format("p:%s, q:%s\n", page, query);
		output += String.format("title : %s\n", list.get(0).getTitle());*/
		
		//return "customer/notice"; //servlet-context.xml���� ������ resolve ���̺귯���� �̿��ؼ� �����Ͽ��⶧���� �ٿ������ִ�!
		return "customer.notice.list"; //tiles�� �������ؼ� tiles �������Ͽ� ������ ��� �ٲ��ش�. ��� tiles resolver�� ��ġ����� �Ѵ�.
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
