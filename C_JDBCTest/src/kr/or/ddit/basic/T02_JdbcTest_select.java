package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_JdbcTest_select {
/*
	문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
	
	문제2) lprod_id값을 2개 입력받아 두 값중 작은 값부터 큰값사이의 자료를 출력하시오
 */
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String userId = "whwndnjs";
			String passWord = "java";
			
			conn = DriverManager.getConnection(url, userId, passWord);
			
			
			stat = conn.createStatement();
			int num;
			System.out.println("lprod_id값 입력");
			num = scan.nextInt();
			String sql = "SELECT * FROM lprod WHERE lprod_id > " + num;
			rs = stat.executeQuery(sql);
			runQuery(rs, sql);
			
			int max, min;
			System.out.println("lprod_id값 입력으로 두사이의 값 입력");
			max = scan.nextInt();
			min = scan.nextInt();
			sql = "SELECT * FROM lprod WHERE lprod_id BETWEEN " + max +" and " + min;
			rs = stat.executeQuery(sql);
			runQuery(rs, sql);
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void runQuery(ResultSet rs, String sql) throws SQLException {
		System.out.println("실행한 쿼리문 : " + sql);
		System.out.println("=== 쿼리문 실행결과 ===");
		
		while (rs.next()) {
			System.out.println("lprod_id : " + rs.getInt("lprod_id"));
			System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
			System.out.println("lprod_nm : " + rs.getString(3));
			System.out.println("----------------------------------------");
		}
		System.out.println("출력끝");
	}
}
