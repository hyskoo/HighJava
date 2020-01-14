package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
	ResourceBundel 객체 => 확장자가 properties인 파일 정보를 일겅와 key값과 value값을 분리한 정보를 갖는 객체
	
			=> 읽어온 파일은 'key값=value값'형태로 되어 있어야 한다.
 */
public class T07_ResourceBundleTest {
	public static void main(String[] args) {
		
		// ResourceBundle객체를 이용하여 파일 읽어오기
		
		// ResourceBundle객체 생성하기
		//	=> 파일을 지정할 때는 '파일명'만 지정하고 확장자는 지정하지 않는다. (이용 : 확장자는 항상 properties이기 때문)
		
		System.out.println(Locale.getDefault()); // 기본 로케일값 출력
		System.out.println(Locale.JAPAN);	// 일본로컬파일값 출력
		
		ResourceBundle bundle = ResourceBundle.getBundle("db"); // 자동으로 Local.getdefault()설정으로 잡힌다.
//		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPAN); // db_ja.properties 파일을 번들객체화.
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		// key값 개수만큼 반복해서 value값 가져오기
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			// key값을 이용하여 value값을 읽어오는 방법 
			// => bundle객체변수 .getString(key값)
			String value = bundle.getString(key);
			System.out.println(key + " => " + value);
		}
		
		System.out.println("출력 끝...");

	}
	
}
