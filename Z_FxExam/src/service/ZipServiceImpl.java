package service;

import java.util.List;
import java.util.Map;

import dao.ZipDao;
import dao.ZipDaoImpl;
import vo.ZipVO;

public class ZipServiceImpl implements ZipService {

	private static ZipServiceImpl instance;
	private ZipServiceImpl() {}
	
	public static ZipService getInstance() {
		if (instance == null) {
			instance = new ZipServiceImpl();
		}
		return instance;
	}
	
	ZipDao  zipDao = ZipDaoImpl.getInstance();
	@Override
	public List<ZipVO> getSearchData(Map<String, String> map) {
		return zipDao.getSearchData(map);
	}
	

}
