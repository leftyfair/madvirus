package chap03.spring;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import chap03.spring.dto.Member;
import chap03.spring.rowMember.MemberMapper;

@Component
public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao() {
	}

	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> members = jdbcTemplate.query("select * from member where email = ?", new MemberMapper(), email);
		return members.isEmpty() ? null : members.get(0);

	}

	public Collection<Member> selectAll() {
		return jdbcTemplate.query("select * from member", new MemberMapper());
	}

	public void insert(Member member) {
		jdbcTemplate.update("insert into member(email, password, name, regdate) values(?,?,?,?)", member.getEmail(),
				member.getPassword(), member.getName(), new Date());

	}

	public void update(Member member) {
		jdbcTemplate.update("update member set name=?, password=? where email=?",
				member.getName(), member.getPassword(), member.getEmail());
		
	}

}
