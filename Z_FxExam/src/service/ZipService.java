package service;

import java.util.List;
import java.util.Map;

import vo.ZipVO;

public interface ZipService {

	List<ZipVO> getSearchData(Map<String, String> map);
 
	
}
