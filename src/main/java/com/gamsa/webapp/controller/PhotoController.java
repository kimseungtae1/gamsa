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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.entity.Photo;

@Controller
@RequestMapping("/photo/*")
public class PhotoController {

	@Autowired
	private PhotoDao photoDao;
	
	
	@RequestMapping(value="upload/reg", method=RequestMethod.GET)
	public String photoReg() {
		
		return "photo.upload.Reg";
	}

	@RequestMapping(value="upload/reg", method=RequestMethod.POST)
	public String noticeReg(
			MultipartHttpServletRequest multipartRequest
			) throws UnsupportedEncodingException {
		
		Iterator<String> itr =  multipartRequest.getFileNames();
	    
	    String filePath = "C:/test"; //�������Ϸ� ����.
	     
	    while (itr.hasNext()) { //���� ���ϵ��� ��� ������.
	         
	        /* ���� �ּ�ó��
	        MultipartFile mpf = multipartRequest.getFile(itr.next());
	        String originFileName = mpf.getOriginalFilename();
	        System.out.println("FILE_INFO: "+originFileName); //���� ���� ����Ʈ ���'
	        */
	         
	        MultipartFile mpf = multipartRequest.getFile(itr.next());
	  
	        String originalFilename = mpf.getOriginalFilename(); //���ϸ�
	  
	        String fileFullPath = filePath+"/"+originalFilename; //���� ��ü ���
	  
	        try {
	            //���� ����
	            mpf.transferTo(new File(fileFullPath)); //�������� �����δ� service���� ó��
	             
	            System.out.println("originalFilename => "+originalFilename);
	            System.out.println("fileFullPath => "+fileFullPath);
	  
	        } catch (Exception e) {
	            System.out.println("postTempFile_ERROR======>"+fileFullPath);
	            e.printStackTrace();
	        }
	                      
	   }
	      
	    return "success";
	}
	
	
}





















