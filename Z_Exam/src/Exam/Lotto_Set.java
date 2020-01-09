package Exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lotto_Set {
	/*
	 로또를 구매하는 프로그램 작성하기
	 
	 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	 입력한 금액에 맞게 로또번호를 출력한다.
	 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	      출력한다.)
	
		==========================
	         Lotto 프로그램
		--------------------------
		 1. Lotto 구입
		 2. 프로그램 종료
		==========================		 
		메뉴선택 : 1  <-- 입력
				
		 Lotto 구입 시작
			 
		(1000원에 로또번호 하나입니다.)
		금액 입력 : 2500  <-- 입력
				
		행운의 로또번호는 아래와 같습니다.
		로또번호1 : 2,3,4,5,6,7
		로또번호2 : 20,21,22,23,24,25
				
		받은 금액은 2500원이고 거스름돈은 500원입니다.
				
	   	 ==========================
	         Lotto 프로그램
		--------------------------
		  1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 2  <-- 입력
			
		감사합니다
	 */
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		Lotto_Set lotto = new Lotto_Set();
		System.out.println("==========================\r\n" + 
						   "	 Lotto 프로그램\r\n" + 
						   "--------------------------\r\n" + 
						   "	 1. Lotto 구입\r\n" + 
						   "	 2. 프로그램 종료\r\n" + 
						   "==========================	");
		int input = lotto.except(scan.nextLine());
		switch (input) {
		case 1:
			lotto.getNumber();
			break;
		case 2:
			System.out.println("감사합니다");
			System.exit(0);
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

	void getNumber() {
		System.out.print("Lotto 구입 시작 \r\n\r\n"
				+ "(1000원에 로또번호 하나입니다.) \n"
				+ "금액 입력 : ");
		int input = except(scan.nextLine());
		int cnt = input / 1000;
		int random = 0;
		
		System.out.println("\n");
		System.out.println("행운의 로또번호는 다음과 같습니다.");
		if (cnt > 0) {
			for (int i = 1; i <= cnt; i++) {
				Set<Integer> set = new HashSet<Integer>();
				while(set.size()<6) {
					random = (int) (Math.random()*45)+1;
					set.add(random);
				}
				List<Integer> list = new ArrayList<Integer>(set);
				Collections.sort(list);
				System.out.println("로또번호"+ i + list.toString());
			}
			System.out.println();
			System.out.println("받은 금액은 " + input + "원이고 거스름돈은 " + input%1000 + "원입니다.");
		} else {
			System.out.println("돈이 부족합니다.");
		}
	}

	int except(String input) {
		int num = 0;
		try {
			num = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
		}
		return num;
	}
}
