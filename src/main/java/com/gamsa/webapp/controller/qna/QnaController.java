package com.gamsa.webapp.controller.qna;




import java.io.Console;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.dao.QnaDao;
import com.gamsa.webapp.dao.QnaReplyDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Qna;
import com.gamsa.webapp.entity.QnaReply;
import com.google.gson.Gson;


@Controller
@RequestMapping("/qna/*")
public class QnaController {
	@Autowired
	private QnaDao qnaDao;
	@Autowired
	private QnaReplyDao qnaReplayDao;
	
	
	@RequestMapping("list")
	public String questionReg(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field, //title을 기본값으로 검색하겠다
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		model.addAttribute("list", qnaDao.getList(page, field, query));
		return "qna.question.list";
	}

	@RequestMapping(value="detail/{id}"/*,method=RequestMethod.GET*/)
	public String noticeDetail(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("question", qnaDao.get(id));
		
		//댓글 모델 설정
		List<QnaReply> list = qnaReplayDao.getList(id);
		model.addAttribute("CommentList",list);
		return "qna.question.detail";
	}

	@RequestMapping(value="reg", method=RequestMethod.GET)
	public String noticeReg() {
		
		return "qna.question.reg";
	}
	
	
	@RequestMapping(value="reg", method=RequestMethod.POST)
	public String noticeReg(
			Qna qna, HttpServletRequest request
			) throws UnsupportedEncodingException {
		qnaDao.insert(qna);
		
		return "redirect:../qna/list";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String noticeEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("question", qnaDao.get(id));
		return "qna.question.edit";
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String noticeEdit(
			Qna qna, HttpServletRequest request
			) throws UnsupportedEncodingException {
		qnaDao.update(qna);
		
		return "redirect:../list";
	}
	
	@RequestMapping("delete/{id}")
	public String noticeDelete(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("list", qnaReplayDao.getList(id));
		
		return "redirect:../list";
	}
	
	
	@RequestMapping(value="comment/save", method=RequestMethod.POST)
    @ResponseBody
    //public String boardReplySave(@RequestParam Map<String , Object>comment_content) {
    public String boardCommentSave(@RequestParam Map<String, Object> objParams,Principal principal) {
    	 

        //정보입력
    	String content = objParams.get("comment_content").toString();
    	String qna_id = objParams.get("qna_id").toString();
    	String writer_id = principal.getName();
   
        int result = qnaReplayDao.insert(content, qna_id, writer_id);
        System.out.println(content);
        System.out.println(qna_id);
        System.out.println(writer_id);
        if(result>0){
        	System.out.println("comment등록에 성공하였습니다.");
        }else{
            System.out.println("comment등록에 실패하였습니다.");
        }
        //return "admin.board.free.detail";
        return "aa";
    }
	
	@RequestMapping(value="comment/update-ajax", method=RequestMethod.GET)
    @ResponseBody
    public String boardCommentUpdate(@RequestParam String qnaId,@RequestParam String cId) {
	
    	//정보입력
    	System.out.println("qnaId : "+qnaId+", id : "+cId);
    	List<QnaReply> list = qnaReplayDao.getUpdateList(qnaId);

    	
    	String json = "";	
		Gson gson = new Gson();
		json = gson.toJson(list);
		
		return json;
    }


	


	
}






















