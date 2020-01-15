package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T03_ArrayListTest {
	public static void main(String[] args) {
		
		/* 5명의 사람 이름을 입력하여 ArrayList에 저장하고 이중에서 '김'씨 성의 이름을 출력하시오
		 *  입력은 Scanner로
		 */
		List<String> list = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
			list.add(scan.nextLine());
		}
		System.out.println(list);
		
		System.out.println("김씨 성 출력");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).substring(0,1).equals("김")) {
				System.out.println(list.get(i));
			}
			if(list.get(i).indexOf("김") == 0) {
				System.out.println(list.get(i));
			}
			if (list.get(i).startsWith("김")) {
				System.out.println(list.get(i));
			}
			if (list.get(i).charAt(0) == '김') {
				System.out.println(list.get(i));
			}
		}
		
	}
}
