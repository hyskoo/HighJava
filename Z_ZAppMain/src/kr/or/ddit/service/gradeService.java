package kr.or.ddit.service;

import java.util.List;
import kr.or.ddit.vo.GradeVO;

public interface gradeService {

	List<GradeVO> getData();

	void insertData(GradeVO gv);

}
