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
				new Object[] {"%"+query+"%" , (page-1)*10},  //ù��° ����ǥ, �ι�° ����ǥ
				BeanPropertyRowMapper.newInstance(Qna.class));
		
		return list;
	}



	@Override
	public Qna get(String id) {
		String sql = "select * from Qna where id=?"; //sql���� ������������ �κ���... object�迭�� ���ڸ� �־������ν� �ذ��Ѵ�!
		
		/*spring�� di����� �̿��Ͽ� �ڵ差�� ���ϼ��ִ�.*/
		/*DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("sist");
		dataSource.setPassword("cclass");*/
		
		/*JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);*/ //database�� �����ϱ� ���� �˷�����Ұ�!
		
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
		
		//���� �Ϸ���? ���� ������ ����. Ȥ�ó� ���� ������� �����°�쿡..
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



	//Transactionó�����3��  4��(@transactional)
	//AOP�� ����ϴ� ���
	

	//Transactionó�����2
	//TransactionTemplate ����ϴ� ���
	/*@Override
	public int insert(Qna Qna) {

		String sql = "insert into Qna(id, title, content, writerId) values(?, ?, ?, ?)";
		
		//Ʈ������� �����ϱ� ���� service�������� ������ ���� �۾�
		String sql1 = "update Member set point=point+1 where id=?";
		
		int result = 0;
		
		result = (int) transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {

				template.update(sql
						, getNextId()		//���������� �̿��ϱ� ���� �޼���
						, Qna.getTitle()
						, Qna.getContent()
						, Qna.getWriterId());
				
				
				template.update(sql1
						, Qna.getWriterId());
				
			}
		});

			return result;
	
	}*/
	
	//Transactionó�����1
	//TransactionManager�� ���� ����ϴ� ���
	/*@Override
	public int insert(Qna Qna) {

		String sql = "insert into Qna(id, title, content, writerId) values(?, ?, ?, ?)";
		
		//Ʈ������� �����ϱ� ���� service�������� ������ ���� �۾�
		String sql1 = "update Member set point=point+1 where id=?";
		
		
		//���۾����� ���� Ʈ����� ó��(spring�� ��� ��� ���ѻ���)
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus state = transactionManager.getTransaction(def);
		
		try {
			int result = template.update(sql
					, getNextId()		//���������� �̿��ϱ� ���� �޼���
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
					, getNextId()		//���������� �̿��ϱ� ���� �޼���
					, title
					, content
					, "1");
			
		
			

		return result;
		

		
			}*/
	   public int insert(String title, String content, String writerId) {

		      return insert(new Qna(title, content, writerId));
		   }
		   
		   
		   
		   //Ʈ����� ó����� 3���� 4��!
		   //AOP�� ����ϴ� ���
		   @Override
		   @Transactional(propagation=Propagation.REQUIRES_NEW)//  ó���� �������� ���������� �Ϸᰡ �ǰ�, ó�� ���� ������ ���� �� ������ �ڵ� rollback ���ֱ� ���� ���ȴ�.
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
