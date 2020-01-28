package kr.or.ddit.basic;

import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) {
		System.out.println("id = " + PrintAnnotation.id); // 프로그램실행시 annotation에있는 상수들이 methodArea에 올라가므로 이렇게 사용가능
		
		
		// reflection 기능을 이용한 메소드 실행
//		Class<Service> cs = Service.class;
//		cs.getDeclaredMethods(); 이걸 줄이면 Service.class.getDeclaredMethods();
		Method[] declaredMethod = Service.class.getDeclaredMethods(); 
		
		for (Method m : declaredMethod) {
			System.out.println(m.getName()); // 메소드명 출력
			for (int i = 0; i < m.getDeclaredAnnotation(PrintAnnotation.class).count(); i++) { // printAnnotation의 count 값만큼....
				System.out.print(m.getAnnotation(PrintAnnotation.class).value()); // printAnnotation의 value 출력
			}
			
			System.out.println();
			
			Class<Service> clazz = Service.class;
			try {
				Service service = (Service) clazz.newInstance();
				m.invoke(service);
			} catch (Exception e) {	
				e.printStackTrace();
			}
			
		}
	/*
	 	Java Reflection에 대하여...
	 	
	 	1. 리플렉션은 클래스 또는 멤버변수, 메소드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
	 	2. Reflection API 또는 java.lang.reflection 패키지와 java.lang.Class를 통해 제공된다.
	 	3. java.lang.Class의 주요 메소드
	 	 - getName(), gerSuperclass(), getInterface(), getMethod(), getModifiers()
	 	4. java.lang.reflect 패키지의 주요 클래스
	 	 - Field, Method, Constructor, Modifier등
	 	5. Reflection API를 이용하면 클래스의 private 메소드나 변수에 접근가능하다.(보안위협)
	 	6. Reflection API의 기능은 뛰어나지만, 약간의 오버헤드가 발생한다. (느린수행속도, 보안취약성, 권한 문제등)
	 		그러므로 가급적 마지막 수단으로 사용하도록 고려되어야 한다.
	 */
	}
}
