package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Photo;

public interface PhotoDao {
	
	int insert(String title, String explain, String writerId);
	int insert(Photo photo);
	String getNextId();
	int getList();


}