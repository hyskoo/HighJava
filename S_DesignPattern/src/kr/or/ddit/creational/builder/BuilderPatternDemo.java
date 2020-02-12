package kr.or.ddit.creational.builder;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();
		
		Meal vegMeal = mealBuilder.prepareVegMeal();
		System.out.println("채직주의자 식사");
		vegMeal.showItems();
		System.out.println("총 비용 : " + vegMeal.getCost() );
		
		Meal NonvegMeal = mealBuilder.prepareNonVegMeal();
		System.out.println("비채직주의자 식사");
		NonvegMeal.showItems();
		System.out.println("총 비용 : " + NonvegMeal.getCost()); 
	}
}
