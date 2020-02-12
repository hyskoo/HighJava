package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class T11_TcpFileClient {

	// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D드라이브의 C_Lib폴더에 저장한다.
	private Socket socket;
	private InputStream in;
	private FileOutputStream fos;
	
	public void clientStart() {
		File file = new File("d:/C_Lib/Tulips.jpg");
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 다운로드 시작....");
			
			fos= new FileOutputStream(file);
			in = socket.getInputStream();
			
			byte[] temp = new byte[1024];
			int length = 0;
			while ((length = in.read(temp)) != -1) {
			/*	
				input.read(temp); // temp배열 크기만큼 자료를 읽어와 temp배열에 저장함.
				
				output.write(temp); // temp배열의 내용을 출력한다.
			*/
				//temp배열의 내용 주에서 0번째부터 length개수만큼 출력한다
				fos.write(temp, 0, length);
			}
			fos.flush();
			System.out.println("파일 다운로드 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
			if (in != null) {
				try { in.close(); } catch (IOException e) { e.printStackTrace(); }
			}
			if (socket != null) {
				try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
	}
	
	public static void main(String[] args) {
		new T11_TcpFileClient().clientStart();
	}
}
