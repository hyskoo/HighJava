package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.member.controller.MemberMain;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {

	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger resultLogger = Logger.getLogger(MemberMain.class);
	
	
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {
		
	}
	
	public static MemberDaoImpl getIntance() {
		if (dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Scanner scan = new Scanner(System.in);
	
	/*
	 * 자원반납 메소드
	 */
	public void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){} 
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = DBUtil2.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) values (?,?,?,?)";
//			String sql = "insert into mymember (mem_id, mem_tel, mem_addr) values (?,?,?,?)";
			sqlLogger.warn("쿼리 : " + sql);
			
			/*
				WARN MemberDaoImpl.insertMember:62 - 쿼리 : insert into mymember (mem_id, mem_tel, mem_addr) values (?,?,?,?)
  				DEBUG MemberDaoImpl.insertMember:70 - 파라미터 : (MemberVO [mem_id = 66, mem_name = 66, mem_tel = 66, mem_addr = 66])
  				java.sql.SQLSyntaxErrorException: ORA-00913: too many values
  				
  				insert쿼리에서 해당하는 컬럼보다 많은 수의 value값을 보내서 생기는 에러
			 */
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());
			
			paramLogger.debug("파라미터 : (" + mv.toString() + ")");
			
			cnt = pstmt.executeUpdate();
			
			resultLogger.warn("결과 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 자원반납
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		try {
			conn = DBUtil2.getConnection();
			String sql = "select count(*) cnt from mymember"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		} finally {
			disConnect();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setMem_id(rs.getString("mem_id"));
				mv.setMem_name(rs.getString("mem_name"));
				mv.setMem_tel(rs.getString("mem_tel"));
				mv.setMem_addr(rs.getString("mem_addr"));
				memList.add(mv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = DBUtil2.getConnection();
			String sql = "update mymember "
					+ "  set "
					+ "  mem_name = ?, "
					+ "  mem_tel = ?, "
					+ "  mem_addr = ? "
					+ "  where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = DBUtil2.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember where 1=1";
			
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += "and mem_id = ?";
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += "and mem_name = ?";
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += "and mem_tel = ?";
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += "and mem_addr like '%' || ? || '%'";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(index++, mv.getMem_id());
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(index++, mv.getMem_name());
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(index++, mv.getMem_tel());
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(index++, mv.getMem_addr());
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO memVO = new MemberVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memList.add(memVO);
			}
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return memList;
	}

}
