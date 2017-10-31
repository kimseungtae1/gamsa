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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


import com.gamsa.webapp.dao.QnaDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Qna;

public class SpringQnaDao implements QnaDao {
	
	@Autowired
	private JdbcTemplate template;
	

	
	
	@Override
	public List<Qna> getList(int page, String field, String query) {

		String sql = "select * from Qna where " + field + " like ? order by regDate desc limit ?,10";
		
		List<Qna> list = template.query(
				sql,
				new Object[] {"%"+query+"%" , (page-1)*10},  //첫번째 물음표, 두번째 물음표
				BeanPropertyRowMapper.newInstance(Qna.class));
		
		return list;
	}



	@Override
	public Qna get(String id) {
		String sql = "select * from Qna where id=?"; //sql문에 정해지지않은 부분은... object배열의 인자를 넣어줌으로써 해결한다!
		
		/*spring의 di기능을 이용하여 코드량을 줄일수있다.*/
		/*DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("sist");
		dataSource.setPassword("cclass");*/
		
		/*JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);*/ //database에 연결하기 위해 알려줘야할것!
		
		Qna Qna = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Qna.class));
		
		/*QnaView Qna = template.queryForObject(
				sql, 
				new Object[] {id},
				new RowMapper<QnaView>() {

					@Override
					public QnaView mapRow(ResultSet rs, int rowNum) throws SQLException {
							
						QnaView Qna = new QnaView();
						Qna.setId(rs.getString("id"));
						Qna.setTitle(rs.getString("title"));
						Qna.setWriterId(rs.getString("writerId"));
						Qna.setContent(rs.getString("content"));
						Qna.setHit(0);
						
						return Qna;
					}
					
				});*/
		
		return Qna;
	}

	@Override
	public int update(String id, String title, String content) {
	
		String sql = "update Qna set title=?, content=? where id=?";
		
		int result = template.update(sql
				, title
				, content
				, id);
		
		//직접 하려면? 거의 쓸일은 없다. 혹시나 위의 방식으로 못쓰는경우에..
		/*int result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement st) throws SQLException {
				st.setString(1, title);
				st.setString(2, content);
				st.setString(3, id);
				
			}
			
		});*/
				
		return result;
	}

	@Override
	public Qna getPrev(String id) {

		String sql = "select * from Qna where id < CAST(? AS UNSIGNED) order by regDate desc limit 1";
		
		Qna Qna = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Qna.class));
		
		return Qna;

	}

	@Override
	public Qna getNext(String id) {

		String sql = "select * from Qna where id > CAST(? AS UNSIGNED) order by regDate asc limit 1";
		
		Qna Qna = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(Qna.class));
		
		return Qna;

	}



	//Transaction처리방법3과  4번(@transactional)
	//AOP를 사용하는 방법
	

	//Transaction처리방법2
	//TransactionTemplate 사용하는 방법
	/*@Override
	public int insert(Qna Qna) {

		String sql = "insert into Qna(id, title, content, writerId) values(?, ?, ?, ?)";
		
		//트랜잭션을 구현하기 위해 service계층으로 나누어 내는 작업
		String sql1 = "update Member set point=point+1 where id=?";
		
		int result = 0;
		
		result = (int) transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {

				template.update(sql
						, getNextId()		//서브쿼리를 이용하기 위한 메서드
						, Qna.getTitle()
						, Qna.getContent()
						, Qna.getWriterId());
				
				
				template.update(sql1
						, Qna.getWriterId());
				
			}
		});

			return result;
	
	}*/
	
	//Transaction처리방법1
	//TransactionManager를 직접 사용하는 방법
	/*@Override
	public int insert(Qna Qna) {

		String sql = "insert into Qna(id, title, content, writerId) values(?, ?, ?, ?)";
		
		//트랜잭션을 구현하기 위해 service계층으로 나누어 내는 작업
		String sql1 = "update Member set point=point+1 where id=?";
		
		
		//수작업으로 해준 트랜잭션 처리(spring의 기능 사용 안한상태)
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus state = transactionManager.getTransaction(def);
		
		try {
			int result = template.update(sql
					, getNextId()		//서브쿼리를 이용하기 위한 메서드
					, Qna.getTitle()
					, Qna.getContent()
					, Qna.getWriterId());
			
			
			result += template.update(sql1
					, Qna.getWriterId());
			
			transactionManager.commit(state);
			
			return result;
		}
		catch (Exception e) {
			transactionManager.rollback(state);
			
			throw e;
		}
		
	}*/

	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from Qna";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}




/*	@Override
	public int insert(String title, String content, String writerId) {

		
		
		String sql = "insert into Qna(id, title, content, writerId) values(?, ?, ?, ?)";
		
		
		
		int result = 0;
	
		result =template.update(sql
					, getNextId()		//서브쿼리를 이용하기 위한 메서드
					, title
					, content
					, "1");
			
		
			

		return result;
		

		
			}*/
	   public int insert(String title, String content, String writerId) {

		      return insert(new Qna(title, content, writerId));
		   }
		   
		   
		   
		   //트랜잭션 처리방법 3번과 4번!
		   //AOP를 사용하는 방법
		   @Override
		   @Transactional(propagation=Propagation.REQUIRES_NEW)//  처리한 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 쿼리를 자동 rollback 해주기 위해 사용된다.
		   public int insert(Qna qna) {

		      String sql = "insert into Qna(id, title, content, writerId) values(?,?,?,?)";
		      
		      
		      int result=template.update(sql, getNextId(), qna.getTitle(), qna.getContent(), "1");
		      
		      
		      

		      

		      return result;
		   }



		@Override
		public Notice delete(String id) {
			// TODO Auto-generated method stub
			return null;
		}

}
