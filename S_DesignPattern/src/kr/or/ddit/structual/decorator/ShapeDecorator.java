package kr.or.ddit.structual.decorator;

public abstract class ShapeDecorator implements Shape{

	protected Shape decoratedShape;
	
	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedShape = decoratedShape;
	}

}
