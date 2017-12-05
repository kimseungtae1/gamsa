package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Reply;

public interface ReplayDao {

	int insert(String content, String photoid, String writerid);
	int insert(Reply reply);
	int update(Reply reply);
 	List<Reply> getList(String qnaId);
 	public List<Reply> getUpdateList(String qnaId);
	int delete(String id);
	String getNextId();

}
