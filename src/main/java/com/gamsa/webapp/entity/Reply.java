package com.gamsa.webapp.entity;

import java.util.Date;

public class Reply {
	
	private String id;
	private String content;
	private Date regDate;
	private String photoId;
	private String writerId;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(String id, String content, Date regDate, String photoId, String writerId) {
		super();
		this.id = id;
		this.content = content;
		this.regDate = regDate;
		this.photoId = photoId;
		this.writerId = writerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	
}
