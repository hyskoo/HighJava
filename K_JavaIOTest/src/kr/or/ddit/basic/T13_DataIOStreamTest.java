package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T13_DataIOStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("d:/D_Other/test.dat");
		
		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해준다.
		DataOutputStream dout = new DataOutputStream(fout);
		
		dout.writeUTF("홍길동"); // 문자열 데이터 출력(UTF-8)
		dout.writeInt(17);	// 정수형으로 데이터 출력
		dout.writeFloat(3.14f); // Float형으로 데이터 출력
		dout.writeDouble(3.14); // Double형으로 데이터 출력
		dout.writeBoolean(true); // 논리형으로 데이터 출력
		
		System.out.println("출력 완료");
		//============================================
		// 출력한 자료 얻어오기
		
		FileInputStream fin = new FileInputStream("d:/D_Other/test.dat");
		
		DataInputStream din = new DataInputStream(fin);
		
		// 데이터를 넣어준 순서대로 읽어야한다.
		System.out.println("문자열 자료 : " + din.readUTF());
		System.out.println("정수형 자료 : " + din.readInt());
		System.out.println("Float형 자료 : " + din.readFloat());
		System.out.println("Double형 자료 : " + din.readDouble());
		System.out.println("논리형 자료 : " + din.readBoolean());
		
	}
}
