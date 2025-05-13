package kr.or.ddit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleDataSource;
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
class JDBCTest {

	@Test
	void testCase1() {
		try {
			// 2번 단계
			Class.forName("oracle.jdbc.OracleDriver"); 
			
			// 3번 단계
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "LYS00";
			String password = "java";
			try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement(); // 4번
			) {
				System.out.println(conn);
				String sql = "select name from V$CONTAINERS";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCase2() {
		try {
			OracleDataSource dataSource = new OracleDataSource(); 
			
			// 3번 단계
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "LYS00";
			String password = "java";
			
			dataSource.setURL(url);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			try (
				Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement(); // 4번
			) {
				System.out.println(conn);
				String sql = "select name from V$CONTAINERS";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
