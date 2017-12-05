package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Qna;

public interface QnaDao {

	Qna get(String id); //mybatis�� ���ӽ��ѵ� �ɶ��� �������̽��� �ٲ㵵 �ȴ�... ������ �������̽��� �ٸ������� ��밡���ϵ��� �Ȱǵ帮�°�,...	
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
