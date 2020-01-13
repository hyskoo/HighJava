package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.properies파일의 내용으로 DB정보를 설정하는 방법
 * 
 * 방법1) properties객체를 이용하기
 */
public class DBUtil2 {
	static Properties prop; // Properties 객체변수 선언

	static {
		prop = new Properties();

		try {
			File file = new File("res/db.properties");
			FileInputStream fin = new FileInputStream(file);
			prop.load(fin);

			Class.forName(prop.getProperty("driver"));

		} catch (FileNotFoundException e) {
			System.out.println("파일이 없거나 입출력 오류입니다/");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결에 실패하셨습니다");
			e.printStackTrace();
			return null;
		}
	}

}
