package chap03.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import chap03.spring.MemberDao;
import chap03.spring.dto.Member;
import chap03.spring.exception.MemberNotFoundException;

@Component
public class ChangePasswordService {
	
	private MemberDao memberDao;
	
	@Autowired
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public ChangePasswordService() {
		
	}
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) 
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
	
	

}
