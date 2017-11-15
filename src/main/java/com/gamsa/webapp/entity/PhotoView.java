package com.gamsa.webapp.entity;

import java.util.Date;

public class PhotoView extends Photo{
	
	private String src;
	private String photoId;
	
	public PhotoView() {
		// TODO Auto-generated constructor stub
	}
	
	public PhotoView(String id, String title, String explain, Date regDate, int liked, String replyId, String writerId) {
		super(id, title, explain, regDate, liked, replyId, writerId);
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	
	
}
