package kr.or.ddit.creational.simpleFactory;

public class FactroyPatternDemo {
	public static void main(String[] args) {
		
		
		// 팩토리 객체 생성
		ShapeFactory factory = new ShapeFactory();
		
		
//		Shape shape1 = factory.getShape("CIRCLE");
		Shape shape1 = new Circle();
		shape1.draw();
		
		Shape shape2 = factory.getShape("RECTANGLE");
		shape2.draw();
		
		Shape shape3 = factory.getShape("SQUARE");
		shape3.draw();
	}
}
