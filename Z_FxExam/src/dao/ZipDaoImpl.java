
package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.ZipVO;

public class ZipDaoImpl implements ZipDao {

	private static ZipDaoImpl instance;
	private SqlMapClient smc;
	
	private ZipDaoImpl(){
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
	
	public static ZipDao getInstance() {
		if (instance == null) {
			instance = new ZipDaoImpl();
		}
		return instance;
	}

	@Override
	public List<ZipVO> getSearchData(Map<String, String> map) {
		List<ZipVO> list = null;
		try {
			list = smc.queryForList("zip.searchData", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
	
	

}
