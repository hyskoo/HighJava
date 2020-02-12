package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
	JSON : JavaScript Object Notation (js 객체 표기법)
	JSON은 데이터를 주고받기 위한 기본 포맷(문법)이다.
	
	JSON에서 value값으로 가능한 데이터 타입
	
		1. String
		2. Number
		3. Object (JSON Object)
		4. Array
		5. Boolean
		6. Null
 */
public class JsonSimpleWriteTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// JSON 데이터 생성
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("name", "홍길동");
		jsonObj.put("job", "학생");
		jsonObj.put("age", 30);
		jsonObj.put("addr", "대전시 중구 대흥동");
		
		// JSONArray 데이터 생성
		JSONArray singerList = new JSONArray();
		
		JSONObject singer = new JSONObject();
		singer.put("name", "푸린");
		singer.put("job", "가수");
		singer.put("age", 40);
		singer.put("gender", "남자");
		singerList.add(singer);
		
		singer = new JSONObject();
		singer.put("name", "나루토");
		singer.put("job", "닌자");
		singer.put("age", 32);
		singer.put("gender", "여자");
		singerList.add(singer);
		
		
		jsonObj.put("singerList", singerList);

		FileWriter fw = new FileWriter("d:/D_Other/myJsonFile.txt");
		fw.write(jsonObj.toString());
		fw.flush();
		fw.close();
		
		System.out.println("JSON객체 내용 출력 : " + jsonObj);
			
			
		
		
		
		
	}
}
