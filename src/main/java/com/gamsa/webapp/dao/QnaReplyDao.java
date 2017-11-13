package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Qna;
import com.gamsa.webapp.entity.QnaReply;

public interface QnaReplyDao {
	int insert(String comment);
	int update(QnaReply qnaReply);
	QnaReply get(String id);
	int delete(String id);
}
