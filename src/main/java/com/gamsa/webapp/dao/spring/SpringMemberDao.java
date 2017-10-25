package com.gamsa.webapp.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gamsa.webapp.dao.MemberDao;
import com.gamsa.webapp.entity.Member;



public class SpringMemberDao implements MemberDao {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public int insert(String id, String pwd, int role) {
		
		return insert(new Member(id, pwd, role, null));
	}

	@Override
	public int insert(Member member) {
		String sql = "insert into Member(id, pwd, role) values(?, ?, ?)";
		
		int result = 0;
	
		result =template.update(sql
					, member.getId()
					, member.getPwd()
					, member.getRole());

		return result;
	}

	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}*/
	
	
	//Transactionó�����1
	//TransactionManager�� ���� ����ϴ� ���
	/*@Autowired
	private PlatformTransactionManager transactionManager;*/
	
	//Transactionó�����2
/*	@Autowired
	private TransactionTemplate transactionTemplate;*/
	
	
}
