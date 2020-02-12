package kr.or.ddit.basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class T13_UDPSocketClient {
	public void start() throws IOException {
		// 소켓객체 생성
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddr = InetAddress.getByName("localhost");
		
		// 데이터가 저장될 공간으로 byte배열을 생성한다.(패킷 수신용)
		byte[] msg = new  byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddr, 8888);
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		datagramSocket.send(outPacket); // dataPacket을 전송한다.
		datagramSocket.receive(inPacket);  // dataPacket을 수신한다.
		
		System.out.println("현재 서버 시간 => " + new String(inPacket.getData())); // new String을 하지않으면 글자깨짐.
		
		datagramSocket.close(); // 소켓 종료
	}   
	public static void main(String[] args) {
		try {
			new T13_UDPSocketClient().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
