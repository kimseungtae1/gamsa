package com.gamsa.webapp.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gamsa.webapp.dao.PhotoDao;
import com.gamsa.webapp.entity.Photo;
import com.gamsa.webapp.entity.PhotoView;

public class SpringPhotoDao implements PhotoDao {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public int insert(String title, String explain, String replyId, String writerId) {
		
		return insert(new Photo(title, explain, replyId, writerId));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int insert(Photo photo) {

		String sql = "insert into Photo(id, title, `explain`, replyId, writerId) values(?, ?, ?, ?, ?)";
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      System.out.println("username = " + user.getUsername());
		  
		int result=template.update(
				sql, 
				getNextId(), 
				photo.getTitle(), 
				photo.getExplain(), 
				"kst",
				user.getUsername());
		
		System.out.println("replyId : " + photo.getReplyId());
		System.out.println("writerId : " + photo.getWriterId());
		
		return result;

	}

	@Override
	public String getNextId() {
		
	String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from Photo";
			
			String result = template.queryForObject(
					sql,
					String.class);
			
			return result;
	}

	@Override
	public PhotoView get(String id) {
		String sql = "select * from PhotoView where id=?";
			
		PhotoView photo = template.queryForObject(
				sql,  
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(PhotoView.class));
		
		return photo;
	}

	@Override
	public String getPhotoNextId() {
		String sql = "select ifnull(max(cast(id as unsigned)),0) from Photo";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	@Override
	public String getWriterId() {
		/*User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    System.out.println("username = " + user.getUsername());*/
	    
		return null;
	}

	@Override
	public String getPhotoWriterId() {
		String sql = "select writerId from Photo where id = (select * from (select ifnull(max(cast(id as unsigned)),0) from Photo) A)";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	@Override
	public List<PhotoView> getList() {

		String sql = "select * from PhotoView order by cast(id as unsigned) asc";
		
		List<PhotoView> list = template.query(
				sql,  
				BeanPropertyRowMapper.newInstance(PhotoView.class));
		
		return list;
	}



	

	/*@Override
	public Photo getwriterId(String id) {
		String sql = "select writerId from Photo where id = ?";
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("username = " + user.getUsername());
		
		Photo photo = template.queryForObject(
				sql,
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Photo.class));
		
		return photo;
	}*/

	
}
