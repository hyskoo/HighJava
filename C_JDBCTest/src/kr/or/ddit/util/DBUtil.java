package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 메소드로 구성된 클래스
 */
public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"whwndnjs", 
					"java");
		} catch (SQLException e) {
			System.out.println("DB연결에 실패하셨습니다");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
