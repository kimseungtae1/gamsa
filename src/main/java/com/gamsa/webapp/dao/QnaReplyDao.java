package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.QnaReply;

public interface QnaReplyDao {
	int insert(String content, String qnaId);
	int insert(QnaReply qnaReply);
	int update(QnaReply qnaReply);
 	List<QnaReply> getList(String qnaId);
	int delete(String id);
	String getNextId();
}
