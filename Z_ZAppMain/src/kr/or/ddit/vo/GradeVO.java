package kr.or.ddit.vo;

public class GradeVO {
	private String name;
	private int kor;
	private int math;
	private int eng;
	
	
	
	public GradeVO() {
		super();
	}
	public GradeVO(String name, int kor, int math, int eng) {
		super();
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	}
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getMath() {
		return math;
	}
	public int getEng() {
		return eng;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	
	
}
