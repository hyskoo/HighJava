package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
	부모 객체의 필드값 처리 방법
	
	
	1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야 한다.
	2. 자식클래스에 writeObject()와 readObject()메소드를 이용하여
	부모객체의 필드값을 처리할 수 있도록 한다.
 */
public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializeTest.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos); 
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); // 직렬화
		
		oos.flush(); // 생략가능
		
		oos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializeTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Child child2 = (Child) ois.readObject();
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("ChildName : " + child2.getChildName());
		ois.close();
		
		
	}
}



/**
 * Serializeable을 구현하지 않은 부모클래스
 */
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

//직렬화를 구분한 자식 클래스
class Child extends Parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -686783374427953199L;
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될때 처음으로 호출됨.
	 * (접근 제어자가 private가 아니면 자동으로 호출되지 않음.)
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException{
		
		// ObjectOutputStream객체의 메소드를 이용하여 부모객체 필드값 처리.
		out.writeUTF(getParentName());
		out.defaultWriteObject();
	}
	
	/**
	 * 역직렬화 될때 자동으로 호출됨.  -  위의 ois.readObject()에서 해당하는 ㅂ분
	 * (접근 제어자가 private가 아니면 자동으로 호출되지 않음.)
	 * 
	 * @param input
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException{
		
		//ObjectInputStream객체의 메소드를 이용하여 부모객체 필드값 처리
		setParentName(input.readUTF());
		input.defaultReadObject();
	}
}