package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 1. jdbc 연동
 * 2. jdbc 연동후 호출
 * 3. jdbc statement객체 이용
 */

public class Hottel_JDBC {
	static Scanner scan = new Scanner(System.in);
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			System.out.println("**************************");
			System.out.println("호텔 문을 열었습니다.");
			System.out.println("**************************\n");
			
			while(true) {
				System.out.println("*******************************************");
				System.out.println("어떤 업무를 하시겠습니까? \n"
						+ "1.체크인 2.체크아웃 3.객실상태 4.업무종료");
				System.out.println("*******************************************");
				System.out.print("메뉴입력 => ");
				int input = Integer.parseInt(scan.nextLine());
				switch (input) {
				case 1:
					add();		
					break;
				case 2:
					del();
					break;
				case 3:
					list();
					break;
				case 4:
					System.out.println("**************************\r\n" + 
										"호텔 문을 닫았습니다.\r\n" + 
										"**************************");
					System.exit(0);
					break;
				default:
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DB연결에 실패하셨습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("잘못된 쿼리입니다.");
			e.printStackTrace();
		}
	}

	private static void del() throws ClassNotFoundException, SQLException {
		PreparedStatement ps;
		Statement st;
		ResultSet rs;
		Connection conn = connect();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int room = Integer.parseInt(scan.nextLine());
		
		String check = "SELECT room_num FROM hotel_mng WHERE room_num = " + room;
		st = conn.createStatement();
		rs = st.executeQuery(check);
		
		if (rs.next() == false) {
			System.out.println(room + "방에는 체크인한 사람이 없습니다.");
			return;
		} else {
			String sql = "DELETE FROM hotel_mng WHERE room_num = " + room;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("체크아웃 되었습니다.");
		}
		
	}

	private static void add() throws ClassNotFoundException, SQLException {
		PreparedStatement ps;
		Statement st;
		ResultSet rs;
		int room;
		String name = "";
		Connection conn = connect();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		room = Integer.parseInt(scan.nextLine());
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		name = scan.nextLine();
		System.out.println();
		
		String check = "SELECT room_num FROM hotel_mng WHERE room_num = " + room;
		st = conn.createStatement();
		rs = st.executeQuery(check);
		if(rs.next() == true) {
			System.out.println("해당 객실에 이미 투숙객이 있습니다.");
			System.out.println();
		} else {
			String sql = "INSERT INTO hotel_mng(room_num, guest_name) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, room);
			ps.setString(2, name);
			ps.executeUpdate();
			
			System.out.println("체크인 되었습니다.");
		}
	}
	
	private static void list() throws ClassNotFoundException, SQLException {
		ResultSet rs;
		Statement st;
		Connection conn = connect();
		st = conn.createStatement();

		String sql = "SELECT * FROM hotel_mng";
		rs = st.executeQuery(sql);
	
		String check = "";
		while (rs.next()) {
			System.out.println("방번호 : "+ rs.getInt(1) + ", 투숙객 : "+ rs.getString(2));
			check = "no";
		}
		if (check.equals("")) {
			System.out.println("현재 투숙객이 존재하지 않습니다.");
			return;
		}
		
		
	}
	
	private static Connection connect() throws ClassNotFoundException, SQLException {
		Connection conn;
		String url = "jdbc:oracle:thin:@localhost:1521/XE";
		String userId = "whwndnjs";
		String passWord = "java";
		
		conn = DriverManager.getConnection(url, userId, passWord);
		
		return conn;
	}

	
}