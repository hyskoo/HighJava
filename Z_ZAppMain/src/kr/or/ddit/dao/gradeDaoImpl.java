package kr.or.ddit.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.GradeVO;

public class gradeDaoImpl implements gradeDao {

	private static gradeDaoImpl instance;
	private SqlMapClient smc;
	
	private gradeDaoImpl(){
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
	
	public static gradeDao getInstance() {
		if (instance == null) {
			instance = new gradeDaoImpl();
		}
		return instance;
	}

	@Override
	public List<GradeVO> getData() {
		List<GradeVO> list = null;
		try {
			list = smc.queryForList("grade.getDataList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertData(GradeVO gv) {
		try {
			smc.insert("grade.insertData", gv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
