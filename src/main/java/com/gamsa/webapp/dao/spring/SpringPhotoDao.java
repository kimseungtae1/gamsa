package com.gamsa.webapp.dao.spring;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Photo;

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
		System.out.println(photo.getWriterId());
		System.out.println(photo.getReplyId());
		
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
