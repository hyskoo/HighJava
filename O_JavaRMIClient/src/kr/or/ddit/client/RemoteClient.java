package kr.or.ddit.client;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.inf.RemoteInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

/*
	클라이언트쪽의 프로젝트에도 서버의 패키지와 같은 구조로 InterFace와 VO파일이 있어야 한다.
 */
public class RemoteClient {
	public static void main(String[] args) {
		// Registry서버에 등록된 객체를 구한다.
		try {
			// 1. 등록된 서버를 찾기위해 Registry객체를 생성한 후 사용할 객체를 불러온다.
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			RemoteInterface clientInf = (RemoteInterface) reg.lookup("server");
			
			// 이제부터 불러온 객체의 메소드를 호출해서 사용할 수 있다.
			int a = clientInf.deRemotePrint("안녕하세요");
			System.out.println("반환값 : " + a);
			System.out.println("=============================================");
			
			List<String> list = new ArrayList<>();
			list.add("대전");
			list.add("대구");
			list.add("부산");
			list.add("독도");
			clientInf.doPrintList(list);
			System.out.println("List 호출 끝...");
			System.out.println("=============================================");
			
			TestVO vo = new TestVO();
			vo.setTestId("dditId");
			vo.setTestNum("12");
			clientInf.doPrintVo(vo);
			System.out.println("===============================================");
			
			// 파일 전송하기
			File[] files = new File[2];
			files[0] = new File("d:/D_Other/Desert.jpg");
			files[1] = new File("d:/D_Other/Hydrangeas.jpg");
			
			FileInfoVO[] fInfo = new FileInfoVO[files.length];
			
			// 2개의 파일을 읽어서 byte[]에 담아서 서버측 메소드에 전달하면 된다.
			FileInputStream fis;
			for (int i = 0; i < files.length; i++) {
				int len = (int) files[i].length(); // 파일 크기 구하기
				fis = new FileInputStream(files[i]);
				byte[] data = new  byte[len];
				
				fis.read(data); // 파일 내용을 읽어서 byte배열에 저장
				
				fInfo[i] = new FileInfoVO();
				fInfo[i].setFileName(files[i].getName());
				fInfo[i].setFileData(data);
			}
			
			clientInf.setFiles(fInfo); // 서버의 파일 저장하는 메솓 호출
			System.out.println("파일 전송 작업 끝...............");
			System.out.println("===============================================");
		} catch (Exception e) {
			
		}
	}
}
