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
import kr.or.ddit.member.controller.MemberMain;

public class BoardDaoImpl implements BoardDao {
	private static final Logger resultLogger = Logger.getLogger(MvcBoard.class);
	
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
			System.out.println("insert 실패");
			resultLogger.error("ERROR 발생"+ e.getMessage() + "파라미터 : " +bv);
//			e.printStackTrace();
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
			resultLogger.error("ERROR 발생" + e.getMessage() + "파라미터 : " +bv);
//			e.printStackTrace();
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
		} catch (SQLException e) {
			System.out.println("검색에 실패하셨습니다.");
			e.printStackTrace();
		}
		return blist;
	}

	
}
