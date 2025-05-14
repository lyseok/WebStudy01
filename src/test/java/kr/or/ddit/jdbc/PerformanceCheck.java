package kr.or.ddit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

class PerformanceCheck {
	static String driverClassName;
	static String url;
	static String username;
	static String password;
	static boolean autoCommit;
	static int minimumIdle;
	static int maximumPoolSize;
	static long connectionTimeout;
	
	@BeforeAll
	static void beforeClass() {
//		Properties : qualified name 의 형태로 파일 접근
//		ResourceBundle :  base name 형태로 파일 접근
		ResourceBundle dbInfo = ResourceBundle.getBundle("kr.or.ddit.db.DBInfo");
		driverClassName = dbInfo.getString("driverClassName");
		url = dbInfo.getString("url");
		username = dbInfo.getString("username");
		password = dbInfo.getString("password");
		autoCommit = Optional.of(dbInfo.getString("autoCommit"))
								.map(Boolean::parseBoolean)
								.orElseThrow();
		minimumIdle = Optional.of(dbInfo.getString("minimumIdle"))
				.map(Integer::parseInt)
				.orElseThrow();
		maximumPoolSize = Optional.of(dbInfo.getString("maximumPoolSize"))
				.map(Integer::parseInt)
				.orElseThrow();
		connectionTimeout = Optional.of(dbInfo.getString("connectionTimeout"))
				.map(Long::parseLong)
				.orElseThrow();
	}
	
	/**
	 * 소요 시간 평균 : 523ms
	 */
	@Test
	void testCase4() {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setAutoCommit(autoCommit);
		ds.setMinimumIdle(minimumIdle); // 초기에 몇개의 connection을 만들 것인가
		ds.setMaximumPoolSize(maximumPoolSize); // 최대 몇개의 connection을 만들 것인가
		ds.setConnectionTimeout(connectionTimeout); // connection이 모두 사용중이면 최대 몇밀리초만큼 대기시킬것인가
		long start = System.currentTimeMillis();
		for(int i = 1; i <= 100; i++) {			
			try (
					Connection conn = ds.getConnection();
					Statement stmt = conn.createStatement(); // 4번
					) {
				System.out.println(conn);
				String sql = "select name from V$CONTAINERS";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.printf("소요시간 : %dms\n", end-start);
	}
	
	/**
	 * 소요 시간 평균 : 330ms
	 */
	@Test
	void testCase1() {
		try {
			// 2번 단계
			Class.forName("oracle.jdbc.OracleDriver"); 
			
			// 3번 단계
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "LYS00";
			String password = "java";
			long start = System.currentTimeMillis();
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
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end-start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 소요 시간 평균 : 370ms
	 */
	@Test
	void testCase2() {
		try {
			// 2번 단계
			Class.forName("oracle.jdbc.OracleDriver"); 
			
			// 3번 단계
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "LYS00";
			String password = "java";
			long start = System.currentTimeMillis();
			try (
				Connection conn = DriverManager.getConnection(url, user, password);
			) {
				for(int i = 1; i <= 100; i++) {
					Statement stmt = conn.createStatement(); // 4번
					System.out.println(conn);
					String sql = "select name from V$CONTAINERS";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						String value = rs.getString("NAME");
						System.out.println(value);
					}					
				}
			}
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end-start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 소요 시간 평균 : 2100ms
	 */
	@Test
	void testCase3() {
		try {
			// 2번 단계
			Class.forName("oracle.jdbc.OracleDriver"); 
			
			// 3번 단계
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "LYS00";
			String password = "java";
			long start = System.currentTimeMillis();
			for(int i = 1; i <= 100; i++) {
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
			}					
			long end = System.currentTimeMillis();
			System.out.printf("소요시간 : %dms\n", end-start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void booltest() {
		
		System.out.println(testbool(3));
		if(testbool(3)) {
			System.out.println("이거 3임");
		}
		
	}
	boolean testbool(int num) {
		return 3 == num;
	}
}
