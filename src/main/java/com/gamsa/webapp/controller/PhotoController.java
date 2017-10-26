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

import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.entity.Photo;

@Controller
@RequestMapping("/photo/*")
public class PhotoController {

	@Autowired
	private PhotoDao photoDao;
	
	
	@RequestMapping(value="upload/reg", method=RequestMethod.GET)
	public String photoReg() {
		
		return "upload.reg";
	}

	@RequestMapping(value="upload/reg", method=RequestMethod.POST)
	public String noticeReg(
			Photo photo, HttpServletRequest request
			) throws UnsupportedEncodingException {
		photoDao.insert(photo);
		
		return "redirect:../index";
	}
	
	
	
	
}





















