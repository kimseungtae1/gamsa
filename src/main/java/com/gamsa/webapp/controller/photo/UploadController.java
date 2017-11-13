package com.gamsa.webapp.controller.photo;




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

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.dao.PhotoUploadDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.PhotoUpload;

@Controller
@RequestMapping("/photo/*")
public class UploadController {

	@Autowired
	private PhotoUploadDao photoUploadDao;
	
	
	@RequestMapping(value="upload", method=RequestMethod.GET)
	public String photoReg() {
		
		return "photo.upload.reg";
	}

	@RequestMapping(value="upload", method=RequestMethod.POST)
	public String noticeReg(
			MultipartFile file,
			PhotoUpload photoUpload,
			HttpServletRequest request
			) throws IOException{

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int nextId = Integer.parseInt(photoUploadDao.getNextId());
		
		ServletContext ctx = request.getServletContext();
		System.out.println(ctx);
        String path = ctx.getRealPath(String.format("/resource/upload/%s/%d", year, nextId));
        System.out.println(path);
         
        String newFileName = ""; // ���ε� �Ǵ� ���ϸ�

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
		
		String fileName = file.getOriginalFilename(); //db�����ϱ����� ������ �Ѿ������ Ȯ���ؾ��Ѵ�.
		System.out.println(fileName);

        String src = path;
        photoUploadDao.insert(new PhotoUpload(nextId, src, "1", "2"));
        
        
		
	    return "redirect:../../index";
	}
	
	
	
	
}




