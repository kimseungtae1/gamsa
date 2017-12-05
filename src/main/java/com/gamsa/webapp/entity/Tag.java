package com.gamsa.webapp.entity;

public class Tag {
	
	private int id;
	private String name;
	private String photoId;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(int id, String name, String photoId) {
		super();
		this.id = id;
		this.name = name;
		this.photoId = photoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	
	
}
