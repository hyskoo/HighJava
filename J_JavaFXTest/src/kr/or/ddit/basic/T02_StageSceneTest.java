package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class T02_StageSceneTest extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox(); // 컨트롤들을 새로로 배치해주는 컨테이너, vertical Box, 반대로 horizental박스도있다, (컨텐츠의 내용정렬방식차이)
		root.setPrefWidth(650); // VBox의 너비
		root.setPrefHeight(150); // VBox의 높이
		root.setAlignment(Pos.CENTER); // 컨트롤들을 가운데 정렬
		root.setSpacing(30); // 컨트롤과 컨트롤 사이의 간격
		
		Label label = new Label();
		label.setText("Hello~  JavaFx");
		label.setFont(new Font(50)); // Font객체를 이용하여 글자크기 설정
		
		Button btn = new Button();
		btn.setText("확 인");
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				// 처리할 내용을 기술하는 영역
//				Platform.exit();
//			}
//		});
		btn.setOnAction(e ->  Platform.exit() );
		
		// VBox에 컨트롤을 추가하기
		root.getChildren().add(label);
		root.getChildren().add(btn);
		
		// VBox를 루트컨테이너로 하는 Scene객체
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Statge외 Scene 연습"); // 창 제목
		primaryStage.setScene(scene); // Stage의 Scene설정
		primaryStage.show(); // 창보이기
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
