package kr.or.ddit.basic;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T03_ProgramingLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// HBox 컨테이너 설정
		HBox hbox = new HBox();
		
		// 안쪽 여백설정
		// Insets객체는 값을 주는 순서가 css와 같다.
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(50);
		
		// 한줄의 데이터를 입력하는 컨트롤 : TextField객체
		TextField textField = new TextField();
		textField.setPrefWidth(200); // 너비 크기 설정
		
		Button btn = new Button("확 인"); // 버튼 객체 생성
		
		// HBox에 추가
		hbox.getChildren().addAll(textField, btn);
		
		// Scene객체 생성
		Scene scene = new Scene(hbox);
		
		primaryStage.setTitle("자바 코드를 이용한 레이아웃 설정하기");
		primaryStage.setScene(scene); //Scene 추가
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
