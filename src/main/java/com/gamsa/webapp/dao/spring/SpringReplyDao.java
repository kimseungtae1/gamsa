package com.gamsa.webapp.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gamsa.webapp.dao.ReplayDao;
import com.gamsa.webapp.entity.Reply;

public class SpringReplyDao implements ReplayDao {
	
	@Autowired
	private JdbcTemplate template;




	@Override
	public int update(Reply Reply) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getNextId() {

		String sql = "select ifnull(max(cast(id as unsigned)),0) + 1 from Reply";
		
		String result = template.queryForObject(
				sql,
				String.class);
		
		return result;
	}

	public List<Reply> getUpdateList(String photoId) {
		String sql = "select * from Reply where photoId=? order by regDate asc;";
		List<Reply> list = template.query(
				sql,
				new Object[] {photoId},  //첫번째 물음표, 두번째 물음표
				BeanPropertyRowMapper.newInstance(Reply.class));
		return list;
	}


	@Override
	public int insert(String content, String photoId, String writerid) {
		String sql = "insert into Reply(id,content,photoId,writerId) values(?,?,?,?)";
		int result = template.update(sql,getNextId(),content,photoId,writerid);
		
		return result;
	}


	@Override
	public int insert(Reply Reply) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Reply> getList(String photoId) {
		// TODO Auto-generated method stub
		return null;
	}





	


}
