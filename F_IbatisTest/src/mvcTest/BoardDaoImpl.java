package mvcTest;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.basic.MemberVO;

public class BoardDaoImpl implements BoardDao {
	
	private static Logger Wlogger = Logger.getLogger("log4j.board.WARN");
	
	private static BoardDaoImpl instance;
	private SqlMapClient smc;
	private BoardDaoImpl(){
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	
	@Override
	public Object insertBoard(BoardVO bv) {
		Object obj = "";
		try {
			obj = smc.insert("board.insertBoard", bv);
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", bv);
		} catch (SQLException e) {
			System.out.println("수정에 실패하셨습니다.");
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			System.out.println("삭제에 실패하셨습니다.");
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public BoardVO getBoardId(int boardNo) {
		BoardVO bvOne = null;
		try {
			bvOne = (BoardVO) smc.queryForObject("board.getBoardNo", boardNo);
			return bvOne;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bvOne;
	}


	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> blist = null;
		try {
			blist = smc.queryForList("board.selectBoard");
			return blist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return blist;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> blist = new ArrayList<BoardVO>();

		try {
			
			blist = smc.queryForList("board.searchBoard", bv);
			
//			conn = DBUtil.getConnection();
//			String sql = "SELECT * FROM jdbc_board WHERE 1=1";
//			
//			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
//				sql += "and board_title LIKE '%'||?||'%'";
//			}
//			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
//				sql += "and board_writer LIKE '%'||?||'%'";
//			}
//			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
//				sql += "and board_content LIKE '%'||?||'%'";
//			}
//			ps = conn.prepareStatement(sql);
//			
//			int index = 1;
//			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
//				ps.setString(index++, bv.getBoard_title());
//			}
//			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
//				ps.setString(index++, bv.getBoard_writer());
//			}
//			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
//				ps.setString(index++, bv.getBoard_content());
//			}
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				BoardVO bv1 = new BoardVO();
//				bv1.setBoard_no(rs.getInt("board_no"));
//				bv1.setBoard_title(rs.getString("board_title"));
//				bv1.setBoard_writer(rs.getString("board_writer"));
//				bv1.setBoard_date(rs.getDate("board_date"));
//				bv1.setBoard_content(rs.getString("board_content"));
//				blist.add(bv1);
//			}
		} catch (SQLException e) {
			System.out.println("검색에 실패하셨습니다.");
			e.printStackTrace();
		}
		return blist;
	}

	
}
