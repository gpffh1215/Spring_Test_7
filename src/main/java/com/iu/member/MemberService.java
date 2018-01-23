package com.iu.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


@Service
public class MemberService {
	
	@Inject
	private MemberDAO memberDAO;
	
	
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberJoin(memberDTO);
	}
	
}
