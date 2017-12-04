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
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,
			Model model
			) {
		model.addAttribute("list", photoUploadDao.getList(page, field, query));
		
		return "photo.list";
	}
	
	@RequestMapping("detail/{id}")	
	public String photoDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("p", photoDao.get(id));
		//model.addAttribute("p", photoUploadDao.get(id));
		
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
		
		photo.setId(photoDao.getPhotoNextId());
		photoUploadDao.update(photo.getId());
		
		photo.setWriterId(photoDao.getPhotoWriterId());
		photoUploadDao.update2(photo.getWriterId());
		//model.addAttribute("user", photoDao.getwriterId());
		
		return "redirect:../../index";
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String photoDelete(
			HttpServletRequest request,
			Model model) {

		photoUploadDao.delete();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int nextId = Integer.parseInt(photoUploadDao.getNextId());
		System.out.println("사진업로드 다음번호" + nextId);
		int photoId = Integer.parseInt(photoDao.getNextId());
		System.out.println("사진 다음번호" + photoId);
		
		ServletContext ctx = request.getServletContext();
		System.out.println(ctx);
        String path = ctx.getRealPath(String.format("/resource/upload/%s/%d", year, nextId));
        System.out.println(path);
        
        File file = new File(path);

        if( file.exists()){ //파일존재여부확인
             
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                 
                File[] files = file.listFiles();
                
                for( int i=0; i<files.length; i++){
                    if( files[i].delete() ){
                        System.out.println(files[i].getName()+" 삭제성공");
                    }else{
                        System.out.println(files[i].getName()+" 삭제실패");
                    }
                }
                 
            }
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
             
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }

		return "";
		
	}
}





















