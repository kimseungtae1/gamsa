package com.gamsa.webapp.controller.photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.dao.PhotoUploadDao;
import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoUpload;

@Controller
@RequestMapping("/photo/*")
public class UploadController {

	
	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoUploadDao photoUploadDao;
	
	
	@RequestMapping(value="upload", method=RequestMethod.GET)
	public String photoReg() {
		
		return "photo.upload.Reg";
	}

	@RequestMapping(value="upload", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String noticeReg(
			MultipartFile file,
			PhotoUpload photoUpload,
			Photo photo,
			HttpServletRequest request
			) throws IOException{		
		
		System.out.println("originalName : "+file.getOriginalFilename());
		System.out.println("size : "+file.getSize());
		System.out.println("contentType : "+file.getContentType());
		
		//사진 저장주소 입력
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
		
		//String writerId = photoDao.getWriterId();
		
        String src = path;
        photoUploadDao.insert(new PhotoUpload(nextId, src, null, null));//id  src  photoId  writerId
        //photoUploadDao.insert(new PhotoUpload(nextId, src, "1", "2"));//id  src  photoId  writerId

	    return "photo.upload.Reg";
	}
	
}




