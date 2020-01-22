package kr.or.ddit.basic;

public class T04_ThreadTest {
/*
	1~20억 까지의 합계를 구하는데 걸리는 시간 체크하기.
	전체 합계를 구하는 작업을 단독으로 했을때(1개의 쓰레드를 사용)와
	여러 쓰레드를 분할해서 작업할때의 시간을 확인해보자.
 */
	public static void main(String[] args) {
		// 싱글 쓰레드 사용
		sumThread sm = new sumThread(1, 2000000000);
		long startTime = System.currentTimeMillis();
		sm.start();
		
		try {
			sm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("싱글 쓰레드시 경과시간 : " + (endTime - startTime));
		System.out.println("\r\n\r\n");
		
		// 여러 쓰레드가 협력해서 처리 할때
		sumThread[] sumThs = new sumThread[] {
			new sumThread(1L, 500000000L),
			new sumThread(500000000L, 1000000000L),
			new sumThread(1000000000L, 1500000000L),
			new sumThread(1500000000L, 2000000000L)
		};
		
		startTime = System.currentTimeMillis();
		for (int i = 0; i < sumThs.length; i++) {
			sumThs[i].start();
		}
		for (int i = 0; i < sumThs.length; i++) {
			try {
				sumThs[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("4개의 다중 쓰레드시 경과시간 : " + (endTime - startTime));
	}
}

class sumThread extends Thread{
	private long max, min;
	
	public sumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for (long i = min; i < max; i++) {
			sum += i;
		}
		System.out.println(min + "~" + max + "까지의 합 : " + sum);
	}
	
}