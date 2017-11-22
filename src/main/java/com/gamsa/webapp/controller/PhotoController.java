package com.gamsa.webapp.controller;




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
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.dao.PhotoUploadDao;
import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoUpload;

@Controller
@RequestMapping("/photo/*")
public class PhotoController {

	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoUploadDao photoUploadDao;
	
	@RequestMapping("list")
	public String photoList(
			/*@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="t", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,*/
			Model model) {

		model.addAttribute("list", photoUploadDao.getList(/*page, field, query*/));

		return "photo.list";
		
	}
	
	@RequestMapping("detail/{id}")
	public String photoDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("p", photoDao.get(id));
		/*model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));*/
		
		//return "customer/notice-detail";
		return "photo.detail";
	}
	
	@RequestMapping(value="upload/reg", method=RequestMethod.GET)
	public String photoReg() {
		
		return "photo.upload.Reg";
	}

	@RequestMapping(value="upload/reg", method=RequestMethod.POST)
	public String photoReg(
			HttpServletRequest request,
			PhotoUpload photoUpload,
			Photo photo
			) throws UnsupportedEncodingException {
		
		photoDao.insert(photo);
		//photoUploadDao.update(photoUpload);
		//model.addAttribute("user", photoDao.getwriterId());
		
		return "redirect:../../index";
	}
	
	@RequestMapping("delete")
	public String photoDelete(
			/*@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="t", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,*/
			Model model) {

		photoUploadDao.delete();
		//model.addAttribute("list", photoUploadDao.getList(/*page, field, query*/));

		return "photo.upload.Reg";
		
	}
}





















