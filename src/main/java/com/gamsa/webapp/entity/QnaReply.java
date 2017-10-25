package com.gamsa.webapp.entity;

import java.util.Date;

public class QnaReply {
	
	private String id;
	private String content;
	private Date regDate;
	private int qnaId;
	private String writerId;
	
	public QnaReply() {
		// TODO Auto-generated constructor stub
	}

	public QnaReply(String id, String content, Date regDate, int qnaId, String writerId) {
		super();
		this.id = id;
		this.content = content;
		this.regDate = regDate;
		this.qnaId = qnaId;
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

	public int getQnaId() {
		return qnaId;
	}

	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	
	
}
