package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	// data의 null이 아닐때 data값을 반환하는 메소드
	public synchronized String getData() {
		if (data == null) {
			try {
				System.out.println("getData => wait()호출");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
			// wait()이 없으면 메소드가 끝나는 순간에 setData가 일시적으로 들어와서 실행될수는 있지만 규칙적으로 되지는 않는다.
		}
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null;
		System.out.println(Thread.currentThread().getName() + " notify()호출");
		notify();
		
		return returnData;
	}
	
	public synchronized void setData(String data) {
		if (this.data != null) {
			try {
				System.out.println("setData => wait()호출");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		notify();
	}
}

// 데이터를 셋팅만 하는 쓰레드
class ProducerThread extends Thread {
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData("+ data + ") 호출");
			
			dataBox.setData(data);
		}
	}
}


// 데이터를 읽어만 오는 메소드

class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : "+ data);
		}
	}
}

