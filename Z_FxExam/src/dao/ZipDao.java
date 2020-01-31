package dao;

import java.util.List;
import java.util.Map;

import vo.ZipVO;

public interface ZipDao {
	
	List<ZipVO> getSearchData(Map<String, String> map);

	
}
