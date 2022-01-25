package chap03.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chap03.spring.MemberDao;
import chap03.spring.dto.Member;
import chap03.spring.dto.RegisterRequest;
import chap03.spring.exception.AlreadyExistingMemberException;

@Service
public class MemberRegisterService {
	
	@Autowired
	private MemberDao memberDao;
	
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public MemberRegisterService() {
		
	}
	
	public void register(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null ) {
			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		memberDao.insert(newMember);
	}
	
}
