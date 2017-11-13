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
import com.gamsa.webapp.entity.Notice;

public class SpringNoticeDao implements NoticeDao {
	
	@Autowired
	private JdbcTemplate template;
	

	@Override
	public List<Notice> getList(int page, String field, String query) {

		String sql = "select * from Notice where " + field + " like ? order by regDate desc limit ?,10";
		
		List<Notice> list = template.query(
				sql,
				new Object[] {"%"+query+"%" , (page-1)*10},  //첫번째 물음표, 두번째 물음표
				BeanPropertyRowMapper.newInstance(Notice.class));
		
		return list;
	}



	@Override
	public Notice get(String id) {
		String sql = "select * from Notice where id=?"; //sql문에 정해지지않은 부분은... object배열의 인자를 넣어줌으로써 해결한다!
		

		
		Notice notice = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Notice.class));
		
		return notice;
	}


	@Override
	public Notice getPrev(String id) {

		String sql = "select * from Notice where id < CAST(? AS UNSIGNED) order by regDate desc limit 1";
		
		Notice notice = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Notice.class));
		
		return notice;

	}

	@Override
	public Notice getNext(String id) {

		String sql = "select * from Notice where id > CAST(? AS UNSIGNED) order by regDate asc limit 1";
		
		Notice notice = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Notice.class));
		
		return notice;

	}





	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from Notice";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "delete from Notice where id=?"; //sql문에 정해지지않은 부분은... object배열의 인자를 넣어줌으로써 해결한다!
		

		
		int result = template.update(sql
				, id);
		
		return result;
	}



	   public int insert(String title, String content, String writerId) {
		   	 
		      return insert(new Notice(title, content, writerId));
		   }
		   
		   
		   

		   @Override
		   @Transactional(propagation=Propagation.REQUIRES_NEW)//  처리한 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 쿼리를 자동 rollback 해주기 위해 사용된다.
		   public int insert(Notice notice) {
			   System.out.println(notice.getWriterId());

		      String sql = "insert into Notice(id, title, content, writerId) values(?,?,?,?)";
		      
		      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		      System.out.println("username = " + user.getUsername());
		      
				
		      
		      int result=template.update(sql, getNextId(), notice.getTitle(), notice.getContent(), user.getUsername());
		      
		      
		      

		      

		      return result;
		   }


		   @Override
		   public int update(String id, String title, String content) {
			   
			   String sql = "update Notice set title=?, content=? where id=?";
			   
			   int result = template.update(sql
					   , title
					   , content
					   , id);
			   
			   
			   return result;
		   }

		@Override
		public int update(Notice notice) {
		      String sql = "update Notice set title=?, content=? where id=?;";
		      
		      
		      int result=template.update(sql, notice.getTitle(), notice.getContent(), notice.getId());
		      
		      
		      

		      

		      return result;
		}

}
