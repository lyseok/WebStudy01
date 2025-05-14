package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

	@Test
	void testNotExist() {
		String username = "a001";
		MemberVO vo = dao.selectMember(username);
		System.out.println(vo);
		assertNull(vo);
//		Optional.ofNullable(dao.selectMember(username))
//				.ifPresent(System.out::println);
	}
	
	@Test
	void testExist() {
		String username = "a001";
		MemberVO vo = dao.selectMember(username);
		assertNotNull(vo);
//		Optional.ofNullable(dao.selectMember(username))
//				.ifPresent(System.out::println);
	}

}
