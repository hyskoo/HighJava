package kr.or.ddit.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

class AES256T {
	private String iv;
	private Key keySpec;
	
	
	public AES256T(String key) throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8"); // utr-8로 인코딩
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		
	/*
		src : 소스 배열.
		srcPos : 소스 배열의 시작 위치.
		dest : 복사할 배열.
		destPos : 복사할 배열의 시작 위치.
		length : 복사할 배열 요소 수
	 */
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		this.keySpec = keySpec;
	}
	
	// 암호화
	public String aesEncode(String str) throws IllegalBlockSizeException, 
												BadPaddingException, UnsupportedEncodingException, InvalidKeyException, 
												InvalidAlgorithmParameterException, NoSuchAlgorithmException, 
												NoSuchPaddingException {
		
		// Cipher은 암호라는 뜻
		// 암호화와 암호 해독을 위한 기능을 제공하는 Cipher객체 생성.
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		// 키와 알고리즘 매개변수 집합을 사용하여 Cipher를 초기화.
		// .init은 이 암호에 알고리즘 매개 변수가 필요하고 매개 변수가 null인 경우, 기본 암호 구현은 필요한 매개 변수 자체를 생성하도록 되어 있다
		//	(공급자별 기본값 또는 랜덤 값 사용).
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		
		
		/* doFinal
			단일 부품 작업에서 데이터를 암호화 또는 해독하거나 다중 부품 작업을 완료한다. 데이터는 이 암호가 어떻게 초기화되었는지에 따라 암호화되거나 암호 해독된다.
			입력 버퍼의 바이트와 이전 업데이트 작업 중에 버퍼링되었을 수 있는 모든 입력 바이트는 패딩(요청된 경우)이 적용된 상태로 처리된다. 
			GCM/CCM과 같은 AEAD 모드를 사용하는 경우, 암호화의 경우 인증 태그를 추가하거나, 암호 해독 시 검증한다. 결과는 새 버퍼에 저장된다.
		
			이 방법은 완료 후, 이 암호 객체를 초기화를 통해 이전에 초기화할 때의 상태로 재설정한다. 
			즉, 개체는 재설정되고 (초기 호출에 지정된 운영 모드에 따라) 더 많은 데이터를 암호화하거나 해독할 수 있다.
			
			참고: 예외가 발생하면 이 암호 객체를 다시 사용하기 전에 재설정해야 할 수 있다.
		 */
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		
		return enStr;
	}
	
	// 복호화
	public String aesDecode(String str) throws NoSuchAlgorithmException, 
												NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
												UnsupportedEncodingException, IllegalBlockSizeException, 
												BadPaddingException {
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
        
        return new String(c.doFinal(byteStr),"UTF-8");
	}
}

public class AES256 {
	public static void main(String[] args) throws Exception {
		String key = "AES256 Is 16Byte Key And Lenght"; 		// key는 16자 이상 
		AES256T aes256 = new AES256T(key);
		
		String text = "그럼되죠! 걱정할필요가 없죠!";
		String encText = aes256.aesEncode(text);
		String decText = aes256.aesDecode(encText);
		
		System.out.println("암호화할 문자 : " + text);
		System.out.println("암호화된 문자 : " + encText);
		System.out.println("복호화된 문자 : " + decText);
	}
}
