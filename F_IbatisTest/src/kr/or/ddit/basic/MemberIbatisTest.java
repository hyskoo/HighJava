package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
	public static void main(String[] args) {
//		iBatis를 이용하여 DB자료를 처리하는 작업순서
//		1. iBatis의 환결설정 파일을 읽어와 실행시킨다.
		try {
//			1-1. xml문서 읽어오기
//			설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
//			1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();	// Reader객체 닫기
			
//		2. 실행할 SQL문에 맞는 쿼리문을 호출해서 일하는 작업을 수행,
			
//			2-1. insert작업연습
			System.out.println("Insert작업 시작...");
			
			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMem_id("d001");
			mv.setMem_name("홍길동");
			mv.setMem_tel("010-5487-2156");
			mv.setMem_addr("대전시 중구 가양동");
			
			// 2) SqlMapClient객채 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식)_ smc.insert("namespace값.id값", 파라미터객체);
			//		  반환값 : 성공하면 null이 반환된다.
			Object obj = smc.insert("memberTest.insertMember", mv);
			if (obj == null) {
				System.out.println("Insert작업 성공");
			} else {
				System.out.println("Insert작업 실패");
			}
			System.out.println("-----------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
