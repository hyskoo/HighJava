package kr.or.ddit.basic;

/*
	함수적 인터페이스 : 추상메소드가 1개만 선언된 인터페이스
 */

@FunctionalInterface
public interface LambdaTestInterface1 {
	// 반환값이 없고 매개변수가 없는 추상메소드 선언
	public void test1();
}

@FunctionalInterface
interface LambdaTestInterface2 {
//	반환값이 없고 매개변수가 있는 추상메소드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
//	반환값이 있고 매개변수가 있는 추상메소드 선언
	public int test(int a, int b);
}

