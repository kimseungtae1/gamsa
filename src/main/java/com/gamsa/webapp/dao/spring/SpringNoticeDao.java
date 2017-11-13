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
				new Object[] {"%"+query+"%" , (page-1)*10},  //ù��° ����ǥ, �ι�° ����ǥ
				BeanPropertyRowMapper.newInstance(Notice.class));
		
		return list;
	}



	@Override
	public Notice get(String id) {
		String sql = "select * from Notice where id=?"; //sql���� ������������ �κ���... object�迭�� ���ڸ� �־������ν� �ذ��Ѵ�!
		

		
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
		String sql = "delete from Notice where id=?"; //sql���� ������������ �κ���... object�迭�� ���ڸ� �־������ν� �ذ��Ѵ�!
		

		
		int result = template.update(sql
				, id);
		
		return result;
	}



	   public int insert(String title, String content, String writerId) {
		   	 
		      return insert(new Notice(title, content, writerId));
		   }
		   
		   
		   

		   @Override
		   @Transactional(propagation=Propagation.REQUIRES_NEW)//  ó���� �������� ���������� �Ϸᰡ �ǰ�, ó�� ���� ������ ���� �� ������ �ڵ� rollback ���ֱ� ���� ���ȴ�.
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
