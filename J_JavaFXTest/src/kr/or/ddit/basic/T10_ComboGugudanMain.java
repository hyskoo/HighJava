package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T10_ComboGugudanMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 300);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(10);
		
		TextArea txtResult = new TextArea();
		txtResult.setPrefSize(200, 200);
		
		Button btnDan = new Button("출력");
		
		ComboBox<Integer> cmbDan = new ComboBox<>();
		cmbDan.setPrefWidth(150);
		
		ObservableList<Integer> list = FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9
				);
		cmbDan.setItems(list);//파라미터에 ObservableList를 담아서 넘어주는 방법
		
		//=======앞에서 한것과는 다른 방법 .
		btnDan.setOnAction(e->{//getSelectedIndex()는 index를 가져옴
			int dan = cmbDan.getSelectionModel().getSelectedItem();
			
			txtResult.setText(dan + "단 \n\n");
			for(int i=1; i<=9; i++) {
				int r = dan * i;
				txtResult.appendText(dan + "*" + i + "=" + r +"\n");
			}
		});
		
		hBox.getChildren().addAll(cmbDan, btnDan);
		
		root.setTop(hBox);
		root.setCenter(txtResult);
		*/
		
		//fxml로 만들어둔 파일을 불러오기
		Parent root = FXMLLoader.load(getClass().getResource("ComboGugudan.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("콤보박스 구구단");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
