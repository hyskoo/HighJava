package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.gradeDao;
import kr.or.ddit.dao.gradeDaoImpl;
import kr.or.ddit.vo.GradeVO;

public class gradeServiceImpl implements gradeService {
	private static gradeServiceImpl instance;
	
	private gradeServiceImpl(){ }
	
	public static gradeService getInstance() {
		if (instance == null) {
			instance = new gradeServiceImpl();
		}
		return instance;
	}
	
	gradeDao gDao = gradeDaoImpl.getInstance();

	@Override
	public List<GradeVO> getData() {
		return gDao.getData();
	}

	@Override
	public void insertData(GradeVO gv) {
		gDao.insertData(gv);
	}
}
