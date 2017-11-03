package com.gamsa.webapp.entity;

public class PhotoUpload {
	
	private int id;
	private String src;
	private String photoId;
	private String writerId;
	
	public PhotoUpload() {
		// TODO Auto-generated constructor stub
	}

	public PhotoUpload(int id, String src, String photoId, String writerId) {
		super();
		this.id = id;
		this.src = src;
		this.photoId = photoId;
		this.writerId = writerId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	
}
