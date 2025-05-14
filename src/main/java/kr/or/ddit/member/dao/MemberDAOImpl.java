package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMember(String username) {
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_id, mem_password, mem_name, mem_mail ");
		sql.append("from member ");
		sql.append("where mem_id = ?");

		/**
		1. 벤더(제조사)의 드라이버를 검색하고 빌드 패스에 추가
		    
		    드라이버? java.sql 패키지의 인터페이스들에 대한 구현체 집합
		    
		2. 드라이버 클래스를 메모리에 로딩 
		3. 드라이버를 통해 Connection 생성
		4. 쿼리 객체 생성 : 쿼리를 컴파일하고, 실행하고, 명령을 전달하는 역할.
		    1. Statement
		    2. PreparedStatement
		    3. CallableStatement
		5. 쿼리 (SQL) 실행
		    - DDL(create, alter, drop)
		    - DML(insert, select, update, delete)
		    - TCL(commit, rollback)
		    1. ResultSet(cursor) executeQuery : select
		    2. int executeUpdate : insert, update, delete
		6. 쿼리 결과(ResultSet) 핸들링 : while 문 형태로 커서의 포인터를 이동시켜 가며 접근함
		7. 쿼리 객체 종료, Connection 종료(try-with-resource 구문으로 해결)
		*/
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			MemberVO vo = null; 
				
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMemId(rs.getString("mem_id"));
				vo.setMemPassword(rs.getString("mem_password"));
				vo.setMemName(rs.getString("mem_name"));
				vo.setMemMail(rs.getString("mem_mail"));
			}
			return vo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}
