package com.gamsa.webapp.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gamsa.webapp.dao.QnaReplyDao;
import com.gamsa.webapp.entity.QnaReply;

public class SpringQnaReplyDao implements QnaReplyDao {
	
	@Autowired
	private JdbcTemplate template;




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






/*	@Override
	 //  @Transactional(propagation=Propagation.REQUIRES_NEW)//  처리한 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 쿼리를 자동 rollback 해주기 위해 사용된다.
	public int insert(QnaReply qnaReply) {
		String sql = "insert into QnaReply(id,content,qnaId,AnswerWriterId) values(?,?,?,?)";
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      System.out.println("username = " + user.getUsername());
	      
	      int result=template.update(sql, getNextId(), qnaReply.getContent(), qnaReply.getQnaId(), user.getUsername());
	      
	      
	      

	      

	      return result;
	}
*/

	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from QnaReply";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	@Override
	public List<QnaReply> getUpdateList(String qnaId) {
		String sql = "select * from QnaReply where qnaId=? order by regDate asc;";
		List<QnaReply> list = template.query(
				sql,
				new Object[] {qnaId},  //첫번째 물음표, 두번째 물음표
				BeanPropertyRowMapper.newInstance(QnaReply.class));
		return list;
	}


	@Override
	public int insert(String content, String qnaId, String writerid) {
		String sql = "insert into QnaReply(id,content,qnaId,answerWriterId) values(?,?,?,?)";
		int result = template.update(sql,getNextId(),content,qnaId,writerid);
		
		return result;
	}


	@Override
	public int insert(QnaReply qnaReply) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<QnaReply> getList(String qnaId) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
