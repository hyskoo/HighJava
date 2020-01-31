package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
//extextends Application해주던것과 똑같이 자동생성
//fx loder
public class ComboGugudanController implements Initializable {
	
	//
	@FXML 
	private ComboBox<Integer> cmbDan;
	@FXML 
	private Button btnDan;
	@FXML 
	private TextArea txtResult;

	//implements Initializable를 해줘야 실행이 가능함
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Integer> list = FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9
				);
		cmbDan.setItems(list);
		
		/*
		btnDan.setOnAction(e->{//getSelectedIndex()는 index를 가져옴
			int dan = cmbDan.getSelectionModel().getSelectedItem();
			
			txtResult.setText(dan + "단 \n\n");
			for(int i=1; i<=9; i++) {
				int r = dan * i;
				txtResult.appendText(dan + "*" + i + "=" + r +"\n");
			}
		});*/
	}

	@FXML //어노테이션
	public void btnDanClicked(ActionEvent event) {
	int dan = cmbDan.getSelectionModel().getSelectedItem();
	
	txtResult.setText(dan + "단 \n\n");
	for(int i=1; i<=9; i++) {
		int r = dan * i;
		txtResult.appendText(dan + "*" + i + "=" + r +"\n");
	}
}
}
