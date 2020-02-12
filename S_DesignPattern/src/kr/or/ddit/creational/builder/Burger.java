package kr.or.ddit.creational.builder;

public abstract class Burger implements Item{
	
	@Override
	public Packing packing() {
		
		return new Wrapper();
	}

	// 추상클래스이므로 일단 안해도된다.
//	@Override
//	abstract public String name();

	@Override
	abstract public float price();
	
}
