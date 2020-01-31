package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberMVCController implements Initializable {

	@FXML
	private Button add;
	@FXML
	private Button upd;
	@FXML
	private Button del;
	@FXML
	private Button conf;
	@FXML
	private Button can;
	
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfTel;
	@FXML
	private TextField tfAddr;
	
	@FXML
	private TableColumn<Member, String> tcId;
	@FXML
	private TableColumn<Member, String> tcName;
	@FXML
	private TableColumn<Member, String> tcTel;
	@FXML
	private TableColumn<Member, String> tcAddr;
	
	@FXML
	private TableView<Member> tvId;
	
	static int flag = 0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Member> list = FXCollections.observableArrayList(
					new Member("1", "1", "1", "11")
				);
		tvId.setItems(list);
		
		tcId.setCellValueFactory(new PropertyValueFactory<>("m_id"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("m_name"));
		tcTel.setCellValueFactory(new PropertyValueFactory<>("m_tel"));
		tcAddr.setCellValueFactory(new PropertyValueFactory<>("m_addr"));
		
		conf.setDisable(true);
		can.setDisable(true);
		
		
		add.setOnAction(e ->{
			conf.setDisable(false);
			can.setDisable(false);
			add.setDisable(true);
			upd.setDisable(true);
			del.setDisable(true);
			
			flag = 1;
		});
		
		upd.setOnAction(e ->{
			conf.setDisable(false);
			can.setDisable(false);
			add.setDisable(true);
			upd.setDisable(true);
			del.setDisable(true);
			
			flag = 2;
		});
		
		del.setOnAction(e -> {
			conf.setDisable(true);
			can.setDisable(true);
			add.setDisable(false);
			upd.setDisable(false);
			del.setDisable(false);
			if (tvId.getSelectionModel().isEmpty()) {
				errMsg("작업 오류", "삭제할 자료를 선택한 후 삭제하세요.");
				return;
			}
			
			list.remove(tvId.getSelectionModel().getSelectedIndex());
			infoMsg("작업 결과", tfId.getText()+"님 정보 삭제 완료.");

			tfId.clear();
			tfName.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		conf.setOnAction(e -> {
			conf.setDisable(true);
			can.setDisable(true);
			add.setDisable(false);
			upd.setDisable(false);
			del.setDisable(false);
			
			if (tfId.getText().isEmpty() 
					|| tfName.getText().isEmpty()
					|| tfTel.getText().isEmpty()
					|| tfAddr.getText().isEmpty()) {
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			if (flag == 1) {
				list.add(new Member(tfId.getText(), tfName.getText(), tfTel.getText(), tfAddr.getText()));
				infoMsg("작업 결과", tfId.getText()+"님 정보 추가 완료.");
			} else if (flag == 2) {
				//==============================
				list.set(tvId.getSelectionModel().getSelectedIndex() ,new Member(tfId.getText(), tfName.getText(), tfTel.getText(), tfAddr.getText()));
				infoMsg("작업 결과", tfId.getText()+"님 정보 추가 완료.");
				//==============================
			}

			tfId.clear();
			tfName.clear();
			tfTel.clear();
			tfAddr.clear();
			
		});
		
		can.setOnAction(e -> {
			conf.setDisable(true);
			can.setDisable(true);
			add.setDisable(false);
			upd.setDisable(false);
			del.setDisable(false);
			
			infoMsg("작업 취소", "해당 작업을 취소하였습니다. 다시입력해주세요");
			
			tfId.clear();
			tfName.clear();
			tfTel.clear();
			tfAddr.clear();
		});
	}
	
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("Info");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	public class Member {
		String m_id;
		String m_name;
		String m_tel;
		String m_addr;
		
		public Member(String m_id, String m_name, String m_tel, String m_addr) {
			this.m_id = m_id;
			this.m_name = m_name;
			this.m_tel = m_tel;
			this.m_addr = m_addr;
		}
		
		public String getM_id() {
			return m_id;
		}
		public String getM_name() {
			return m_name;
		}
		public String getM_tel() {
			return m_tel;
		}
		public String getM_addr() {
			return m_addr;
		}
		public void setM_id(String m_id) {
			this.m_id = m_id;
		}
		public void setM_name(String m_name) {
			this.m_name = m_name;
		}
		public void setM_tel(String m_tel) {
			this.m_tel = m_tel;
		}
		public void setM_addr(String m_addr) {
			this.m_addr = m_addr;
		}

	}
}
