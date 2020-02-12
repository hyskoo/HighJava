package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class T19_pieChartTest extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		
		PieChart pieChart = new PieChart();

		// 차트가 나탈 데이터 구성하기
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("포도", 10000),
				new PieChart.Data("사과", 13000), new PieChart.Data("복숭아", 14000), new PieChart.Data("파파야", 15000),
				new PieChart.Data("메론", 60000));
		pieChart.setTitle("과일 가격");
		pieChart.setLabelLineLength(50);
		pieChart.setLegendSide(Side.RIGHT); // 범례가 나타날 위치
		pieChart.setData(pieChartData); // 데이터 설정

		// %를 띄우기 위한 라벨
		Label caption = new Label("");
		caption.setTextFill(Color.BLACK);
		caption.setStyle("-fx-font: 24 arial;");

		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
				double total = 0;
				for (PieChart.Data d : pieChart.getData()) {
					total += d.getPieValue();
				}
				caption.setTranslateX(e.getSceneX());
				caption.setTranslateY(e.getSceneY());
				String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
				caption.setText(text);
			});
		}

		root.getChildren().addAll(pieChart, caption);
		
		Scene scene = new Scene(root);

		primaryStage.setTitle("pieChart Test");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
