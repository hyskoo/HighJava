package Exam;

import java.util.HashSet;
import java.util.Set;

public class SetMethod {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		Set<String> setCopy = new HashSet<>();		
		set.add("SET/ADD");
		setCopy.add("SC1");
		setCopy.add("SC2");
		setCopy.add("SC3");
		set.add("SET2");
		set.addAll(setCopy);
		
		// Set에는 순서가 없으므로 출력했을때 list와 다르다.
		System.out.println(set);
		
		// 반환 값이 [ T/F ] 형식이다.
		set.contains("SC2");
		set.containsAll(setCopy);
		
		// 객체단위로 비교가 가능하다.
		set.equals(setCopy);
		
		
		set.hashCode(); // 저장된 위치의 해시코드값을 가져온다.
		set.isEmpty();  // set 객체가 비었는지 확인한다. 비어있으면 True 반환
		

		
		set.size(); // set의 크기
				
		System.out.println(set.toString());
		
		
		System.out.println(set.retainAll(setCopy));
		
		Object[] oArr = set.toArray();				// 배열로 만든다. (Object 타입)
		String[] sArr = set.toArray(new String[0]); // 배열로 만든다. (new String[0]. 즉, 길이가 0인 String타입 배열을 인자로 사용함으로써 String[] 배열로 만든다.)
		
		set.iterator().next();     // 값을 가져온다.
		set.iterator().hasNext();  // 다음이 있는지 여부 [T/F]
		
//		iterator의 진화형. list의 listLiterator와 같은 계열이다.
		set.spliterator().estimateSize();
//		set.spliterator().getComparator();   // 예외를 던지는 것.

		
	}
}