package kr.or.ddit.structual.decorator;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		
		
		System.out.println("==========================");
		System.out.println("일반 원 그리기 시작"); 
		circle.draw();
		System.out.println("==========================");
		
		System.out.println("경계선 빨간색인 원 그리기 시작");
		System.out.println("==========================");
		redCircle.draw();
		System.out.println("==========================");
		
		System.out.println("경계선 빨간색인 직사각형 그리기 시작");
		System.out.println("==========================");
		redRectangle.draw();
		System.out.println("==========================");
		
	}
}
