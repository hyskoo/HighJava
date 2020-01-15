package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
	제네릭 클래스를 만드는 방법
형식)
	Class 클래스명<제네릭타입글자> {
		제네릭타입글자 변수명;   // 변수 선언에 제니릭을 사용할 경우
		...
		
		제네릭타입글자 메소드명() { // 반환값이 있는 메소드에서 사용
			...
			return 값;
		}
		...
	}
-- 제네릭타입 글자 --
T => Type
K => Key
V => Value
E => Element(자료구조에 들어가는 첫들을 나타낼때 사용)	
 */

class NonGericClass{
	private Object val;

	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGeneric<T>{
	private T val;

	public T getVal() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}
}


public class T01_GenericTest {
	public static void main(String[] args) {
		NonGericClass ng1 = new NonGericClass();
		ng1.setVal("가나다라");
		
		NonGericClass ng2 = new NonGericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 =>" + irtnNg2);
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제네릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제네릭 정수형 반환값 : " + irtnNg2);
		
		
		
		
		
	}
}
