package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class T18_BarChartTest  extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// x축, y축을 이용한 Chart는 해당 축 객체를 생성한다.
		
		// 축의 값이 줄 문자열일때 사용하는 객체
		CategoryAxis xAxis = new CategoryAxis();
		
		// 축의 값이 숫자일때 사용하는 객체
		NumberAxis yAxis = new NumberAxis();
		
		// 위에서 만든 축 정보를 이용한 barChart객체 생성
		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
		
		bc.setTitle("차트 Title");
		xAxis.setLabel("나라");
		yAxis.setLabel("가격");
		
		//BarChart에 나타날 데이터 구성하기
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		
		ser1.setName("2015년");
		ser1.getData().add(new XYChart.Data<String, Number>("호주", 55000));
		ser1.getData().add(new XYChart.Data<String, Number>("미국", 45000));
		ser1.getData().add(new XYChart.Data<String, Number>("중국", 35000));
		ser1.getData().add(new XYChart.Data<String, Number>("일본", 25000));
		ser1.getData().add(new XYChart.Data<String, Number>("독일", 15000));
		
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		ObservableList<XYChart.Data<String, Number>> ser2List = FXCollections.observableArrayList();
		
		ser2List.addAll(
				new XYChart.Data<>("호주", 56000),
				new XYChart.Data<>("미국", 46000),
				new XYChart.Data<>("중국", 36000),
				new XYChart.Data<>("일본", 26000),
				new XYChart.Data<>("독일", 16000)
				);
		ser2.setName("2016년");
		ser2.setData(ser2List);
		
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
		ser3.setName("2017년");
		ser3.getData().add(new XYChart.Data<>("호주", 57000));
		ser3.getData().add(new XYChart.Data<>("미국", 47000));
		ser3.getData().add(new XYChart.Data<>("중국", 37000));
		ser3.getData().add(new XYChart.Data<>("일본", 27000));
		ser3.getData().add(new XYChart.Data<>("독일", 117000));
		
		bc.getData().addAll(ser1, ser2, ser3);
		
		
		Scene scene = new Scene(bc, 800, 600);
		
		primaryStage.setTitle("BarChart Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
