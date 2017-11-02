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
			Model model
			) {
		//model.addAttribute("list", photoDao.getList());
		
		return "photo.list";
		
	}
	
	@RequestMapping(value="upload/reg", method=RequestMethod.GET)
	public String photoReg() {
		
		return "photo.upload.Reg";
	}

	@RequestMapping(value="upload/reg", method=RequestMethod.POST)
	public String photoReg(
			//MultipartHttpServletRequest multipartRequest,
			PhotoUpload photoUpload,
			MultipartFile file,
			HttpServletRequest request
			) throws IOException {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nextId = photoDao.getNextId();
		
		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/upload/photo/%s/%s", year, nextId));		
		System.out.println(path);
		
		File f = new File(path); //������ ���������� ���� ���ϰ�ü ����
			
		if(!f.exists()) {
			if(!f.mkdirs()) //mkdirs() -> ������ ������
				System.out.println("���丮�� ������ ���� �����ϴ�.");
		}
		
		path += File.separator + file.getOriginalFilename();
		File f2 = new File(path);
		
		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);
		
		byte[] buf = new byte[1024];
		
		int size = 0;
		while((size = fis.read(buf)) > 0)
			fos.write(buf, 0, size);
		
		
		fis.close();
		fos.close();
		
		String fileName = file.getOriginalFilename(); //db�����ϱ����� ������ �Ѿ������ Ȯ���ؾ��Ѵ�.
		System.out.println(fileName);
		
		photoUploadDao.insert(new PhotoUpload());
		//photoUploadDao.insert(new PhotoUpload(row, fileName, null, null)); //id, src, photoId, writerId
		
		
		
		
		/*
		Iterator<String> itr =  multipartRequest.getFileNames();
	    
	    String filePath = "C:/test"; //�������Ϸ� ����.
	     
	    while (itr.hasNext()) { //���� ���ϵ��� ��� ������.
	         
	        //���� �ּ�ó��
	        //MultipartFile mpf = multipartRequest.getFile(itr.next());
	        //String originFileName = mpf.getOriginalFilename();
	        //System.out.println("FILE_INFO: "+originFileName); //���� ���� ����Ʈ ���'
	        
	         
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
	      
	    return "success";*/
	    
	    return "";
	}
	
	
}





















