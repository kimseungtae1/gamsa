package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Qna;

public interface QnaDao {

	Qna get(String id); //mybatis에 종속시켜도 될때는 인터페이스를 바꿔도 된다... 하지만 인터페이스는 다른데서도 사용가능하도록 안건드리는게,...	
	int delete(String id);
	int update(String id, String title, String content);
	Qna getPrev(String id);
	Qna getNext(String id);
	String getNextId();
	List<Qna> getList(int page, String field, String query);
	int insert(String title, String content, String writerId);
	int insert(Qna qna);
	int update(Qna qna);
	int getCount();	

}
