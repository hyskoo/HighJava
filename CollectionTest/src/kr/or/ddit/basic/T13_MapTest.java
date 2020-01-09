package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T13_MapTest {
	public static void main(String[] args) {
	/*
		Map => key값과 Value값을 한쌍으로 관리하는 객체
			   key값은 중복을 허용하지 않고 순서가 없다. (Set의 특징)
			   value값은 중복을 허용한다.
	 */
		Map<String, String> map = new HashMap<String, String>();
		
//		자료 추가 => put(K,V)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
//		자료 수정 => 데이터를 저장할때 key값이 같으면 나중에 입력한 값이 저장된다.
//				  => put(수정할 key, 새로운 Value)
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
//		자료 삭제 => remove(삭제할 Key);
		map.remove("name");
		System.out.println("map => " + map);
		
//		자료 읽기 => get(K)
		System.out.println("addr = " + map.get("addr"));
		System.out.println("==================================");
		
		/*
			Key값을 읽어와 자료를 출력하는 방법
			
			1. keySet()메소드 이용하기
			keySet() => Map의 Key값들만 읽어와 Set형으로 반환한다.
		 */
		Set<String> keyset = map.keySet();
		
		System.out.println("Iterator를 이용한 방법");
		
		Iterator<String> it = keyset.iterator();
		while (it.hasNext()) {
			String key = it.next();
			
		}
		System.out.println("---------------------------------------------");
		
		
//			2. Set형의 데이터를 '향상된 for문'으로 처리하면 Iterator를 사용하지 않아도 됨.
		System.out.println("향상된 for문을 사용");
		for (String key : keyset) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("---------------------------------------------");
		
//			3. value값만 일겅와 출력하기 => values()
		System.out.println("value() 이용");
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("---------------------------------------------");
		
		
//			4. Map에는 Entry라는 내부 class가 만들어져 있다. 이 Entry class는 key와 value라는 멤버변수로 구성되어있다.
//			   Map에서 Entry클래스를 Set형식으로 지정하여 관리한다.
		
//			   Entry객체 전체를 가져오기 ( 가져온 Entry들은 Set형식으로 되어 있다.) => entrySet() 사용
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		// 가져온 Entry객체들을 순서대로 처리하기 위해서 Itrerator객체로 변환
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while (entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("Key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 
	}
}
