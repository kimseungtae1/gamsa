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
			//MultipartHttpServletRequest file,
			MultipartFile file,
			PhotoUpload photoUpload,
			HttpServletRequest request
			) throws IOException {
		//System.out.println(file);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int nextId = Integer.parseInt(photoUploadDao.getNextId());
		
		ServletContext ctx = request.getServletContext();
		System.out.println(ctx);
        String path = ctx.getRealPath(String.format("/resource/upload/%s/%d", year, nextId));
        System.out.println(path);
         
        String newFileName = ""; // 업로드 되는 파일명

        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
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
		
		String fileName = file.getOriginalFilename(); //db연동하기전에 파일이 넘어오는지 확인해야한다.
		System.out.println(fileName);
		
       /* Iterator<String> files = file.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
        	
            MultipartFile mFile = file.getFile(uploadFile);
            fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            //newFileName = System.currentTimeMillis()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
            if(fileName != null && !fileName.equals("")) {
            	if(new File(path + fileName).exists())
            		newFileName = fileName + "_" + System.currentTimeMillis();
            	
	            try {
	                //mFile.transferTo(new File(path+fileName));
	            	mFile.transferTo(new File(path+fileName));
	            	File A = new File(path+fileName);
	            	mFile.transferTo(A);
	            	//((MultipartFile) dir).transferTo(new File(path+fileName));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
            }
        }*/
        //file.getFileNames() 이게 이상한거
        String src = path;
        photoUploadDao.insert(new PhotoUpload(nextId, src, "1", "2"));
		
		
		
		
		
		/*

		// 저장 경로 설정
		ServletContext ctx = request.getServletContext();
		System.out.println(ctx);
        String path = ctx.getRealPath(String.format("/resource/upload/%s/%d", year, nextId));
        System.out.println(path);
         
        String newFileName = ""; // 업로드 되는 파일명
        String fileName = "";
        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
        //path += File.separator + file.getFileNames();
        
        Iterator<String> files = file.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
        	
            MultipartFile mFile = file.getFile(uploadFile);
            fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            //newFileName = System.currentTimeMillis()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
            if(fileName != null && !fileName.equals("")) {
            	if(new File(path + fileName).exists())
            		newFileName = fileName + "_" + System.currentTimeMillis();
            	
	            try {
	                //mFile.transferTo(new File(path+fileName));
	            	mFile.transferTo(new File(path+fileName));
	            	File A = new File(path+fileName);
	            	mFile.transferTo(A);
	            	//((MultipartFile) dir).transferTo(new File(path+fileName));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
            }
        }
        //file.getFileNames() 이게 이상한거
        String src = path + File.separator + fileName;
        photoUploadDao.insert(new PhotoUpload(nextId, src, "1", "2"));*/

	    return "redirect:../../index";
	}
}





















