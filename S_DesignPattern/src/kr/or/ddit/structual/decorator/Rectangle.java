package kr.or.ddit.structual.decorator;

public class Rectangle implements Shape{
	@Override
	public void draw() {
		System.out.println("직사각형 출력");
	}
}
