package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCopy {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
		
		int c;
		
		while ((c = fis.read()) != -1) {
			fos.write(c);
		}
		
		fis.close();
		fos.close();

	}
}
