package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.QnaReply;

public interface QnaReplyDao {
	int insert(String content, String qnaId, String writerid);
	int insert(QnaReply qnaReply);
	int update(QnaReply qnaReply);
 	List<QnaReply> getList(String qnaId);
 	List<QnaReply> getUpdateList(String qnaId, String cId);
	int delete(String id);
	String getNextId();
}
