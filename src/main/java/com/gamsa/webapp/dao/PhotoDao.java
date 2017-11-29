package com.gamsa.webapp.dao;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoView;

public interface PhotoDao {
	
	int insert(String title, String explain, String replyId, String writerId);
	int insert(Photo photo);
	String getNextId();
	//Photo getwriterId(String id);
	//List<PhotoUpload> getList(int page, String field, String query);
	PhotoView get(String id);
	String getPhotoNextId();
	String getWriterId();
	String getPhotoWriterId();
	List<PhotoView> getList();
}