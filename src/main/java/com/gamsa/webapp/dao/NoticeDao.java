package com.gamsa.webapp.dao;

import com.gamsa.webapp.entity.Notice;

public interface NoticeDao {
	

	Notice get(String id); //mybatis�� ���ӽ��ѵ� �ɶ��� �������̽��� �ٲ㵵 �ȴ�... ������ �������̽��� �ٸ������� ��밡���ϵ��� �Ȱǵ帮�°�,...	
	Notice delete(String id);
	int update(String id, String title, String content);
	
	Notice getPrev(String id);
	Notice getNext(String id);
	
	int insert(String title, String content, String writerId);
	int insert(Notice notice);
	String getNextId();


}