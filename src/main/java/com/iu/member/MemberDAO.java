package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="MemberMapper.";
	
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		return  sqlSession.insert(NAMESPACE+"insert", memberDTO);
	}
	public void memberLogin() throws Exception{
		
	}

	public void memberUpdate() throws Exception{
		
	}
	
	public void memberDelete() throws Exception{
		
	}
	
	public void memberLogOut() throws Exception{
		
	}
	
	
	
	
}
