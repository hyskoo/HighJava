package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil2;

public class BoardDaoImpl implements BoardDao {
	private static BoardDaoImpl instance;
	private BoardDaoImpl(){}
	
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	/*
	 * 자원반납 메소드
	 */
	public void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(st!=null)try{ st.close(); }catch(SQLException ee){}
		if(ps!=null)try{ ps.close(); }catch(SQLException ee){} 
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	@Override
	public boolean getBoardNo(int boardNo) {
		boolean chk = false;
		try {
			conn = DBUtil2.getConnection();
			String sql = "select count(*) cnt from jdbc_board where Board_no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			
			rs = ps.executeQuery();
			
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return chk;
	}
	
	@Override
	public void insertBoard(String title, String writer, String cont) {
		try {
			conn = DBUtil2.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, writer);
			ps.setString(3, cont);
			int cnt = ps.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("글 작성에 성공하셨습니다.");
			} else {
				System.out.println("글 작성에 실패하셨습니다.");
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
			
			
		} catch (SQLException e) {
			System.out.println("글 작성에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	@Override
	public void updateBoard(String title, String cont, int boardNo) {
		try {
			conn = DBUtil2.getConnection();
			String sql = "UPDATE jdbc_board SET board_title = ?, board_content = ?, WHERE board_no = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, cont);
			ps.setInt(3, boardNo);
			int cnt = ps.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("수정에 성공하셨습니다.");
			} else {
				System.out.println("수정에 실패하셨습니다.");
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
			
			
		} catch (SQLException e) {
			System.out.println("수정에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	
}	
