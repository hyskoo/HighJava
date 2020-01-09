package Exam;

import java.util.ArrayList;
import java.util.List;

public class ListMethod {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list.add("a");
		list2.add(0, "A");
		list2.add("B");
		list.addAll(list2);
		list2.addAll(0, list);
		
		System.out.println(list.toString());
		System.out.println(list2.toString());
		
//		list.clear();
		
//		contains : 데이터가 안쪽에 들어있는지를 확인 T/F
		System.out.println(list.contains("a1"));
		System.out.println(list.containsAll(list2));
			
		System.out.println(list.equals(list2)); // equals(obj) : 두 객체의 내용이 같은지 확인 T/F
	
		System.out.println(list.isEmpty());	// list에서 없는지 확인. 없으면 T  있으면 F
		System.out.println(list.indexOf("A0")); // 해당데이터의 위치를 확인
		System.out.println(list.lastIndexOf("A0"));	// list의 마지막 요소부터 역방향으로 해당 객체의 위치 반환
		list.get(0); // 0번 인덱스에 있는 값을 가져온다.
		

//		list.remove(0);
//		list.remove(list2);
//		list.removeAll(list2);
		
		list.retainAll(list2);	//list2에 있는 값을 list에 포함시킨다.
		list.set(0, "aa");	// list의 0번째 인덱스의 값을 aa로 변경한다.
		list.size(); 	// list의 크기를 반환한다.
		list.sort(null);  // list를 정렬한다. comparator을 이용해서 기준을 줄수도 있다. null이면 오름차순정렬
		
		System.out.println();
		System.out.println(list.toString());
		System.out.println(list.subList(0, 1)); // 0 ~ 2-1번까지. 출력된다.
		System.out.println();
		/*
			1. List를 toArray 메서드에 파라메터로 넘어가는 배열 객체의 size만큼의 배열로 전환한다.
			2. 단, 해당 List size가 인자로 넘어가는 배열 객체의 size보다 클때, 해당 List의 size로 배열이 만들어진다.
			3. 반대로 해당 List size가 인자로 넘어가는 배열객체의 size보다 작을때는, 인자로 넘어가는 배열객체의 size로 배열이 만들어진다.
		*/
		Object[] arr = list.toArray();		// 오브젝트형 배열로 전환되어서 담는다.
		String[] str = list.toArray(new String[0]);
		
		
		
		System.out.println(list.hashCode()); // 해시코드 출력 ..Object객체에 있는것
		// List의 객체에 접글할 수 있는 ListIterator를 반환  iterator : 데이터를 순차적으로 처리할수있게 해주는 객체
		System.out.println(list.iterator()); 
		list.listIterator(); 
		list.listIterator(0);
		list.replaceAll(e -> e.toLowerCase());
		System.out.println(list.spliterator());

	}
}
