package com.gamsa.webapp.entity;
import java.util.Date;

public class NoticeView {
   private String id;
   private String title;
   private String content;
   private Date regDate;
   private String writerId;
   private int hit;

   public NoticeView() {
	   // TODO Auto-generated constructor stub
   }

   

	public NoticeView(String id, String title, String content, Date regDate, String writerId, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.writerId = writerId;
		this.hit = hit;
	}


	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getWriterId() {
		return writerId;
	}
	
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	   
	   
   
}