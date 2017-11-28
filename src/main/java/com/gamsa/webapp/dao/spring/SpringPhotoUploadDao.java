package com.gamsa.webapp.dao.spring;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.gamsa.webapp.dao.NoticeDao;
import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.dao.PhotoUploadDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoUpload;
import com.gamsa.webapp.entity.PhotoView;

public class SpringPhotoUploadDao implements PhotoUploadDao{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public int insert(PhotoUpload photoUpload) {
		
		String sql = "insert into PhotoUpload(id, src, photoId, writerId) values(?, ?, ?, ?)";
		
		int result = 0;
	
		result =template.update(sql
					, photoUpload.getId()
					, photoUpload.getSrc()
					, photoUpload.getPhotoId()
					, photoUpload.getWriterId());

		return result;
	}

	@Override
	public int insert(int id, String src, String photoId, String writerId) {
		
		return insert(new PhotoUpload(id, src, photoId, writerId));
	}


	@Override
	public List<PhotoView> getList(/*int page, String field, String query*/) {
		//String sql = "select * from PhotoView order by cast(id as unsigned) desc limit ?,10";
		//String sql = "select * from PhotoView order by regDate desc limit ?,10";
		String sql = "select * from PhotoView order by cast(id as unsigned) desc";
		
		List<PhotoView> list = template.query(
				sql,
				BeanPropertyRowMapper.newInstance(PhotoView.class));
		
		return list;
	}
	
	
	@Override
	public String getNextId() {
		
		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from PhotoUpload";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	@Override
	public String getPhotoId() {
		String sql = "select writerId from Photo where id=?";
		
		String result = template.queryForObject(
				sql, 
				new Object[] {},
				String.class);
		return result;
	}

	@Override
	public int delete() {
		String sql = "delete from PhotoUpload where id = (select * from (select ifnull(max(cast(id as unsigned)),0) from PhotoUpload) A)";
		
		int result = 0;
		
		result = template.update(sql);

		return result;
	}

	@Override
	public int update(String id) {
		String sql = "update PhotoUpload set photoId = ? where id = (select ifnull(max(cast(id as unsigned)),0) from Photo)";  
	      
		int result=template.update(sql
				, id);
		System.out.println("id : "+id);

		return result;
	}

	@Override
	public int update2(String id) {
		String sql = "update PhotoUpload set writerId = ? where id = (select ifnull(max(cast(id as unsigned)),0) from Photo)";  
	      
		int result=template.update(sql
				, id);
		System.out.println("writerId : "+id);

		return result;
		
	}

	/*@Override
	public int update(PhotoUpload photoUpload) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String photoId, String writerId, String id) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/*@Override
	public int update(String photoId) {
		return update(photoId);
	}*/
	
	//사진 내용 입력후 폼 전송시에 실행되는 photoUpload의 photoId와 writerId가 photo의 id에 맞춰서 수정되는 부분 ==> view에서 합쳐진다
	//update PhotoUpload set photoId = (select ifnull(max(cast(id as unsigned)),0) from Photo) , writerId = 'admin' where id = (select ifnull(max(cast(id as unsigned)),0) from Photo);
	/*@Override
	public int update() {
		String sql = "update PhotoUpload set photoId = ? where id = (select * from (select ifnull(max(cast(id as unsigned)),0) from Photo) A)";  
			      
		int result=template.update(sql
				, );
		System.out.println(photo.getId());

		return result;
	}
*/

}
