package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T04_ArrayListTest {
	public static void main(String[] args) {
		/*
		 *  1. 5명의 별명을 입력하여 ArrayList에 저장하고, 별명의 길이가 제일긴 별명 출력
		 *  	단, 각별명의 길이는 모두 다르게 입력한다.
		 *  
		 *  2. 문제1에서 별명의 길이가 같은 것을 여러개 입려했을때도 처리되도록 하시오.
		 */
		
		List<String> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("별명을 5회 입력하세요");
		for (int i = 0; i < 5; i++) {
			list.add(scan.nextLine());
		}
		
		String Max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (Max.length() < list.get(i).length()) {
				Max = list.get(i);
			}
		}
		System.out.println(Max);
		
		System.out.println("----------동일한 길이인 것들 출력------");
		for (int i = 0; i < list.size(); i++) {
			if (Max.length() == list.get(i).length()) {
				System.out.println(list.get(i));
			}
		}
		
		

		
		
		
		
	}
}
