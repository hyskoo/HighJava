package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class T03_JdbcTest_insert {
/*
	lprod_id : 101, lprod_gu : N101, lprod_nm : 농산물
	lprod_id : 102, lprod_gu : N102, lprod_nm : 수산물
	lprod_id : 103, lprod_gu : N103, lprod_nm : 축산물
 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String userId = "whwndnjs";
			String passWord = "java";
			
			conn = DriverManager.getConnection(url, userId, passWord);
/*
			stat = conn.createStatement();
			
			String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm)"
					+ " VALUES (101,'N101','농산물')";

			// SQL문이 select문이 아닐 경우에는 excuteUpdate()를 사용한다
			// executeUpdate()는 실행에 성공한 레코드 수를 반환한다.
			int cnt = stat.executeUpdate(sql);
			System.out.println("첫번째 반환값 : " + cnt);
			
			sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm)"
					+ " VALUES (102,'N102','수산물')";
			cnt = stat.executeUpdate(sql);
			System.out.println("두번째 반환값 : " + cnt);
			
			sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm)"
					+ " VALUES (103,'N103','축산물')";
			cnt = stat.executeUpdate(sql);
			System.out.println("세번째 반환값 : " + cnt);
*/
/*						
			// PreparedStatement 객체를 이용한 자료 추가 방법

			// SQL문 작성시 데이터가 들어갈자리에 물음표(?)를 넣는다.
			String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm)"
					+ " VALUES (?, ?, ?)";
			// PreparedStatement 객체를 생성할때 SQL문을 넣어서 생성한다.
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			pstmt.setInt(1, 101);
			pstmt.setString(2, "N101");
			pstmt.setString(3, "농산물");
				
			// 데이터를 셋팅한후 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();

			System.out.println("첫번째 반환값 : " + cnt);
			// ---------------------------------------------------------
			
			pstmt.setInt(1, 102);
			pstmt.setString(2, "N102");
			pstmt.setString(3, "수산물");
			
			cnt = pstmt.executeUpdate();

			System.out.println("두번째 반환값 : " + cnt);
			// ---------------------------------------------------------
			
			pstmt.setInt(1, 103);
			pstmt.setString(2, "N103");
			pstmt.setString(3, "축산물");
			
			cnt = pstmt.executeUpdate();

			System.out.println("세번째 반환값 : " + cnt);
			// ---------------------------------------------------------
*/
			stat = conn.createStatement();
			
			String lprod_gu = "N103"; // 사용자 입력값
			String sql = "select * from lprod where lprod_gu = ? and lprod_nm = '축산물'"; 
			System.out.println("실행할 쿼리 : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lprod_gu);
			rs = pstmt.executeQuery();
			
			System.out.println("--------실행결과-----------");
			while (rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString(3));
				System.out.println("----------------------------------------");
			}
			
			System.out.println("작업완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
