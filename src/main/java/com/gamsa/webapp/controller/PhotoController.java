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
			/*MultipartHttpServletRequest multipartRequest,
			MultipartFile file,
			HttpServletRequest request,
			String url,
			String file2*/
			MultipartHttpServletRequest file,
			PhotoUpload photoUpload,
			HttpServletRequest request
			) throws IOException {
		System.out.println(file);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int nextId = Integer.parseInt(photoUploadDao.getNextId());
		

		 // 저장 경로 설정
        //String root = file.getSession().getServletContext().getRealPath("/");
		ServletContext ctx = request.getServletContext();
        String path = ctx.getRealPath(String.format("/resource/upload/%s/", year));
        System.out.println(path);
         
        String newFileName = ""; // 업로드 되는 파일명
        File dir = new File(path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
        path += File.separator + file.getFileNames();
        
        Iterator<String> files = file.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
                         
            MultipartFile mFile = file.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
             
            try {
                mFile.transferTo(new File(path+fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        photoUploadDao.insert(new PhotoUpload(nextId, path, "1", "2"));

	    return "redirect:../../index";
	}
		
/*		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nextId = photoDao.getNextId();
		
		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/upload/photo/%s/%s", year, nextId));		
	    url = path;
		System.out.println(path);
		
		File f = new File(path); //연도별 폴더생성을 위한 파일객체 생성
			
		if(!f.exists()) {
			if(!f.mkdirs()) //mkdirs() -> 폴더를 만들어라
				System.out.println("디렉토리를 생성할 수가 없습니다.");
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
		file2= fileName;
		System.out.println(fileName);
		
		photoUploadDao.insert(new PhotoUpload());*/
		//photoUploadDao.insert(new PhotoUpload(row, fileName, null, null)); //id, src, photoId, writerId
		
		
		
		
		
		/*Iterator<String> itr =  multipartRequest.getFileNames();
	    
	    String filePath = "C:/test"; //설정파일로 뺀다.
	     
	    while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
	         
	        //기존 주석처리
	        //MultipartFile mpf = multipartRequest.getFile(itr.next());
	        //String originFileName = mpf.getOriginalFilename();
	        //System.out.println("FILE_INFO: "+originFileName); //받은 파일 리스트 출력'
	        
	         
	        MultipartFile mpf = multipartRequest.getFile(itr.next());
	  
	        String originalFilename = mpf.getOriginalFilename(); //파일명
	  
	        String fileFullPath = filePath+"/"+originalFilename; //파일 전체 경로
	        System.out.println("fileFullPath => "+fileFullPath);
	        try {
	            //파일 저장
	            mpf.transferTo(new File(fileFullPath)); //파일저장 실제로는 service에서 처리
	             
	            System.out.println("originalFilename => "+originalFilename);
	            System.out.println("fileFullPath => "+fileFullPath);
	  
	        } catch (Exception e) {
	            System.out.println("postTempFile_ERROR======>"+fileFullPath);
	            e.printStackTrace();
	        }
	                      
	   }*/
		
	    
/*	    return "";*/
}





















