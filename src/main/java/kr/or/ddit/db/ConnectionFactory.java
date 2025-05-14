package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Factory
 * 
 * A a = new A();
 * 1. A라는 클래스를 메모리에 로딩
 * 2. 메모리에 로딩된 클래스를 참조하여 객체를 생성함
 * 3. a라는 변수에 공간을 형성하고, 2번 객체의 레퍼런스를 a 변수에 할당함
 */
public class ConnectionFactory {
	private static HikariDataSource ds;

	static {
		ResourceBundle dbInfo = ResourceBundle.getBundle("kr.or.ddit.db.DBInfo");
		String driverClassName = dbInfo.getString("driverClassName");
		String url = dbInfo.getString("url");
		String username = dbInfo.getString("username");
		String password = dbInfo.getString("password");
		boolean autoCommit = Optional.of(dbInfo.getString("autoCommit"))
								.map(Boolean::parseBoolean)
								.orElseThrow();
		int minimumIdle = Optional.of(dbInfo.getString("minimumIdle"))
				.map(Integer::parseInt)
				.orElseThrow();
		int maximumPoolSize = Optional.of(dbInfo.getString("maximumPoolSize"))
				.map(Integer::parseInt)
				.orElseThrow();
		long connectionTimeout = Optional.of(dbInfo.getString("connectionTimeout"))
				.map(Long::parseLong)
				.orElseThrow();
		
		ds = new HikariDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setAutoCommit(autoCommit);
		ds.setMinimumIdle(minimumIdle); // 초기에 몇개의 connection을 만들 것인가
		ds.setMaximumPoolSize(maximumPoolSize); // 최대 몇개의 connection을 만들 것인가
		ds.setConnectionTimeout(connectionTimeout); // connection이 모두 사용중이면 최대 몇밀리초만큼 대기시킬것인가
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
