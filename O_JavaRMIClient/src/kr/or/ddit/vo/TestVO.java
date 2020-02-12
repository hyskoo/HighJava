package kr.or.ddit.vo;

import java.io.Serializable;

/*
	RMI에서 데이터 전달용으로 사용할 객체
	이 객체는 네트워크를 통해서 전달되어야 하기때문에 '직렬화'가 필요하다.
	그래서 Serializable을 구현한 형태로 작성한다.
 */
public class TestVO implements Serializable{
	private static final long serialVersionUID = -4450847804930422099L;
	
	private String testId;
	private String testNum;
	
	
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestNum() {
		return testNum;
	}
	public void setTestNum(String testNum) {
		this.testNum = testNum;
	}
	
	
	
	
}
