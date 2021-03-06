package chap03.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chap03.spring.MemberDao;
import chap03.spring.dto.Member;

@Component
public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberPrinter print;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Autowired
	public void setPrinter(MemberPrinter print) {
		this.print = print;
	}

	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음");
		}
		print.print(member);
		System.out.println();
	}
	

}
