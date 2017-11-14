package com.gamsa.webapp.entity;

import java.util.Date;

public class Photo {
	
	private String id;
	private String title;
	private String explain;
	private Date regDate;
	private int liked;
	private String replyId;
	private String writerId;
	
	public Photo() {
		// TODO Auto-generated constructor stub
	}

	
	public Photo(String id, String title, String explain, Date regDate, int liked, String replyId, String writerId) {
		super();
		this.id = id;
		this.title = title;
		this.explain = explain;
		this.regDate = regDate;
		this.liked = liked;
		this.replyId = replyId;
		this.writerId = writerId;
	}

	public Photo(String title, String explain, String writerId) {
		super();
		this.title = title;
		this.explain = explain;
		this.writerId = writerId;
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

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	
	
}
