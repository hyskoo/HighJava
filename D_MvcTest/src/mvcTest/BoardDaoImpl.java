package mvcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.basic.MemberVO;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements BoardDao {
	
	private static BoardDaoImpl instance;
	private BoardDaoImpl(){}
	
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	
	private Connection conn;  // Connection 객체  JDBC연결을 위해서 java에서 제공해주는 객체. getConection()로 DB를 연결한다.
	private Statement st;
	private PreparedStatement ps; // Statement객체에서 SQL Injection문제 발생으로 인해서 발생된것. sql문을 선언한후 값을 넣는 방식이기에 보안성이 높다.
	private ResultSet rs;

	/*
	 * 자원반납 메소드 : 사용한 자원을 필수적으로 반납을 해야만 하므로 재활용성을 위해서 메소드로 만들었다.
	 */
	public void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(st!=null)try{ st.close(); }catch(SQLException ee){}
		if(ps!=null)try{ ps.close(); }catch(SQLException ee){} 
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, bv.getBoard_title());
			ps.setString(2, bv.getBoard_writer());
			ps.setString(3, bv.getBoard_content());
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("글 작성에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}


	@Override
	public boolean getBoardId(int boardNo) {
		boolean chk = false;
		try {
			conn = DBUtil3.getConnection();
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
	public List<BoardVO> getBoardList() {
		List<BoardVO> blist = new ArrayList<BoardVO>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board";
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			
			while (rs.next()) {
				
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getDate("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				blist.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return blist;
	}


	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE jdbc_board SET board_title = ?, board_content = ? WHERE board_no = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, bv.getBoard_title());
			ps.setString(2, bv.getBoard_content());
			ps.setInt(3, bv.getBoard_no());
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("수정에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}


	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			cnt = ps.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("성공적으로 삭제하였습니다.");
			} else {
				System.out.println("삭제에 실패하셨습니다.");
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
			
			
		} catch (SQLException e) {
			System.out.println("삭제에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}


	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> blist = new ArrayList<BoardVO>();

		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM jdbc_board WHERE 1=1";
			
			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				sql += "and board_title LIKE '%'||?||'%'";
			}
			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				sql += "and board_writer LIKE '%'||?||'%'";
			}
			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				sql += "and board_content LIKE '%'||?||'%'";
			}
			ps = conn.prepareStatement(sql);
			
			int index = 1;
			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				ps.setString(index++, bv.getBoard_title());
			}
			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				ps.setString(index++, bv.getBoard_writer());
			}
			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				ps.setString(index++, bv.getBoard_content());
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardVO bv1 = new BoardVO();
				bv1.setBoard_no(rs.getInt("board_no"));
				bv1.setBoard_title(rs.getString("board_title"));
				bv1.setBoard_writer(rs.getString("board_writer"));
				bv1.setBoard_date(rs.getDate("board_date"));
				bv1.setBoard_content(rs.getString("board_content"));
				blist.add(bv1);
			}
		} catch (SQLException e) {
			System.out.println("검색에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return blist;
	}

	
}
