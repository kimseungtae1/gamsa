package com.gamsa.webapp.dao;

import com.gamsa.webapp.entity.Member;

public interface MemberDao {

	int insert(String id, String pwd, String name, String phone, String email);

	int insert(Member member);

	Member get(String id);

}
