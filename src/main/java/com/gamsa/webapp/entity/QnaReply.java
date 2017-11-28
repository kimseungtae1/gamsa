package com.gamsa.webapp.entity;

import java.util.Date;

public class QnaReply {
	
	private String id;
	private String content;
	private Date regDate;
	private String qnaId;
	private String answerWriterId;
	
/*	private String id;
	private String content;
	private Date date;
	private String freeId;
	private String writerId;*/
	
	
	public QnaReply() {
	}



	public QnaReply(String content, String qnaId) {
		super();
		this.qnaId = qnaId;
		this.content = content;
	}



	public QnaReply(String id, String content, Date regDate, String qnaId, String answerWriterId) {
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

	public String getQnaId() {
		return qnaId;
	}

	public void setQnaId(String qnaId) {
		this.qnaId = qnaId;
	}

	public String getanswerWriterId() {
		return answerWriterId;
	}

	public void setanswerWriterId(String answerWriterId) {
		this.answerWriterId = answerWriterId;
	}
	
	
	
}
