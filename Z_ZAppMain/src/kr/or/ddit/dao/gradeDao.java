package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.GradeVO;

public interface gradeDao {

	List<GradeVO> getData();

	void insertData(GradeVO gv);

}
