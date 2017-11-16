package com.gamsa.webapp.dao;

import java.util.List;

import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoUpload;
import com.gamsa.webapp.entity.PhotoView;

public interface PhotoUploadDao {

	int insert(PhotoUpload photoUpload);
	
	int insert(int id, String src, String photoId, String writerId);

	String getNextId();

	List<PhotoView> getList(int page, String field, String query);
	
}