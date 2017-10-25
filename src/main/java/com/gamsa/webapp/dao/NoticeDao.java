package com.gamsa.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gamsa.webapp.entity.Notice;

public interface NoticeDao {
	List<Notice> getList(@Param("page") int page, String field, String query); //mybatis�� ���ӽ��ѵ� �ɶ��� �������̽��� �ٲ㵵 �ȴ�.
	int getCount();
	
	Notice get(String id); //mybatis�� ���ӽ��ѵ� �ɶ��� �������̽��� �ٲ㵵 �ȴ�... ������ �������̽��� �ٸ������� ��밡���ϵ��� �Ȱǵ帮�°�,...
	
	int update(String id, String title, String content);
	
	Notice getPrev(String id);
	Notice getNext(String id);
	
	int insert(String title, String content, String writerId);
	int insert(Notice notice);
	String getNextId();

}