package Exam;

import java.util.Arrays;

/*
	문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
	
	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
 */
public class PlanetTest {
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private double num;
		
		private Planet(double num) {
			this.num = num;
		}
		
		public double getRadius(){
			return num;
		}
		public double getArea(Planet p) {
			return p.getRadius()*p.getRadius()*Math.PI;
		}
	}
	
	public static void main(String[] args) {
		Planet p = Planet.토성;
		for (Planet s : Planet.values()) {
			System.out.println(s.name() + " / " + s.getArea(s));
		}
		System.out.println(Arrays.toString(Planet.values()));
		System.out.println(p.name());
		System.out.println(p.ordinal());
		System.out.println(p.getArea(p));
		
	}
}
