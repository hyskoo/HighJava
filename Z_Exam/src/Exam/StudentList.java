package Exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {
/*
	  학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	  Student클래스를 만든다.
	  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
	  이 Student객체들은 List에 저장하여 관리한다.
	  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	  총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	  (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	  (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	   총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student(3, "삼돌이", 33, 33, 33));
		list.add(new Student(4, "사순이", 44, 44, 44));
		list.add(new Student(1, "일등이", 11, 11, 11));
		list.add(new Student(5, "오오미", 55, 55, 55));
		list.add(new Student(2, "홍진호", 55, 55, 55));
		
		System.out.println("====================================== 정렬 전 ======================================");
		for (Student s : list) {
			sortRank(list);
			System.out.println(s.toString());
		}
		System.out.println();
		System.out.println("====================================== 학번 정렬 후 ======================================");
		Collections.sort(list);
		for (Student s : list) {
			sortRank(list);
			System.out.println(s.toString());
		}
		System.out.println();
		System.out.println("====================================== 총점 정렬 후 ======================================");
		Collections.sort(list, new StuTotalSort());
		for (Student s : list) {
			sortRank(list);
			System.out.println(s.toString());
		}
	}

	static void sortRank(List<Student> list) {
		for(int i=0; i<list.size(); i++) {
			int num = 1;
			for(int j=0; j<list.size(); j++) {
				if(list.get(i).getTotal() < list.get(j).getTotal()) {
					num++;
				}
			}
			list.get(i).setRank(num);
		}
	}

}

class StuTotalSort implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		int total1 = o1.getTotal();
		int total2 = o2.getTotal();
		if (total1 == total2) {
			return Integer.compare(o1.getId(), o2.getId()) * -1;
		}
		return Integer.compare(total2, total1);   // 위처럼 *(-1)을 하지 않을려면 순서를 바꿔주면 된다.
	}
}

class Student implements Comparable<Student>{
	private Integer id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank = 1;
	

	@Override
	public int compareTo(Student o) {
		return this.id.compareTo(o.id);  // 오름차순.  *(-1)을 하면 내림차순
		// 1이 반환이 되면, 자리 바꾼다. 즉, 자리를 바꾸면서 오름차순으로 정렬한다.
		// -1과 0은 자리를 바꾸지 않는다.
	}
	
	Student(Integer id, String name, int kor, int eng, int math){
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String toString() {
		
		return "학번:" + id + "  "
				+ "이름:" + name+ "  "
				+ "국어점수:" + kor+ "  "
				+ "영어점수:" + eng+ "  "
				+ "수학점수:" + math+ "  "
				+ "총점:" + total+ "  "
				+ "등수:" + rank;
	}
}
