package kr.or.ddit.basic;

public class T08_ThreadPriorityTest {
	public static void main(String[] args) {
		ThreadTest1 th1 = new ThreadTest1();
		ThreadTest2 th2 = new ThreadTest2();
		
		th1.setPriority(10);
		th1.setPriority(1);
		
		System.out.println("th1의 우선순위 : " + th1.getPriority());
		System.out.println("th2의 우선순위 : " + th2.getPriority());
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
	}
}

// 대문자를 출력하는 쓰레드
class ThreadTest1 extends Thread{
	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(ch);
			
			// 아무것도 하지않는 반복문
			for (long i = 0; i < 1000000000; i++) { }
		}
	}
}

// 소문자를 출력하는 쓰레드
class ThreadTest2 extends Thread{
	@Override
	public void run() {
		for (char ch = 'a'; ch <= 'z'; ch++) {
			System.out.print(ch);
			
			// 아무것도 하지않는 반복문
			for (long i = 0; i < 1000000000; i++) { }
		}
	}
}