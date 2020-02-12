package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("d:/D_Other/print.txt");
		FileOutputStream fout2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		PrintStream out = new PrintStream(fout);
		
		out.print("안녕하세요. printStream입니다 1 \r\n");
		out.println("안녕하세요. printStream입니다 2");
		out.println("안녕하세요. printStream입니다 3");
		
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8"));
		
		writer.print("안녕하세요. printWriter입니다 1 \r\n");
		writer.println("안녕하세요. printWriter입니다 2 \r\n");
		writer.println("안녕하세요. printWriter입니다 3 \r\n");
		writer.close();
		
	}
}
