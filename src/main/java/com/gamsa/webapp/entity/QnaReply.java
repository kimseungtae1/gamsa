package com.gamsa.webapp.entity;

import java.util.Date;

public class QnaReply {
	
	private String id;
	private String content;
	private Date regDate;
	private int qnaId;
	private String answerWriterId;
	
	public QnaReply() {
		// TODO Auto-generated constructor stub
	}

	public QnaReply(String id, String content, Date regDate, int qnaId, String answerWriterId) {
		super();
		this.id = id;
		this.content = content;
		this.regDate = regDate;
		this.qnaId = qnaId;
		this.answerWriterId = answerWriterId;
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

	public String getanswerWriterId() {
		return answerWriterId;
	}

	public void setanswerWriterId(String answerWriterId) {
		this.answerWriterId = answerWriterId;
	}
	
	
	
}
