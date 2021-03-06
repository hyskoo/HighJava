package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation()
	public boolean method1() {
		System.out.println("메소드1에서 출력되었습니다.");
		return true;
	}
	@PrintAnnotation("%")   // 타입요소 이름이 value인 경우만 쓰면 생략이 가능
	public boolean method2() {
		System.out.println("메소드2에서 출력되었습니다.");
		return true;
	}
	
	@PrintAnnotation(value="#", count=25)
	public boolean method3() {
		System.out.println("메소드3에서 출력되었습니다.");
		return true;
	}
}
