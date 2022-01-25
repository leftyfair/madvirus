package chap03.spring.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chap03.spring.MemberDao;
import chap03.spring.dto.Member;

@Component
public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	@Autowired
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public MemberListPrinter() {
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		for (Member m : members) {
			printer.print(m);
		}
	}
	

}
