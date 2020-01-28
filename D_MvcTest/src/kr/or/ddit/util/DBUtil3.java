package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil3 {
	static ResourceBundle bundle;	// ResourceBundle 객체 선언
	
	static {
		bundle = ResourceBundle.getBundle("db");
		try {
			Class.forName(bundle.getString("driver"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			// db.properties파일에 있는 내용을 가져오는것. ResuorceBundle 객체를 통해서 db라는 이름의 properties파일을 들고온다.
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결에 실패하셨습니다");
			e.printStackTrace();
			return null;
		}
	}
}
