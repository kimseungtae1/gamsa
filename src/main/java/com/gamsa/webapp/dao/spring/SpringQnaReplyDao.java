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


import com.gamsa.webapp.dao.QnaDao;
import com.gamsa.webapp.dao.QnaReplyDao;
import com.gamsa.webapp.entity.Notice;
import com.gamsa.webapp.entity.Qna;
import com.gamsa.webapp.entity.QnaReply;

public class SpringQnaReplyDao implements QnaReplyDao {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public QnaReply get(String id) {
		String sql = "select * from QnaReply where id=?"; //sql문에 정해지지않은 부분은... object배열의 인자를 넣어줌으로써 해결한다!
		
		
		QnaReply qnaReply = template.queryForObject(
				sql, 
				new Object[] {id},
				BeanPropertyRowMapper.newInstance(QnaReply.class));
		
		
		return qnaReply;
	}
	


	@Override
	public int update(QnaReply qnaReply) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int insert(String content, String qnaId) {
		// TODO Auto-generated method stub
		return insert(new QnaReply(content, qnaId));
	}



	@Override
	 //  @Transactional(propagation=Propagation.REQUIRES_NEW)//  처리한 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 쿼리를 자동 rollback 해주기 위해 사용된다.
	public int insert(QnaReply qnaReply) {
		String sql = "insert into QnaReply(id,content,qnaId,AnswerWriterId) values(?,?,?,?)";
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      System.out.println("username = " + user.getUsername());
	      
	      int result=template.update(sql, getNextId(), qnaReply.getContent(), qnaReply.getQnaId(), user.getUsername());
	      
	      
	      

	      

	      return result;
	}


	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from QnaReply";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}
	


}
