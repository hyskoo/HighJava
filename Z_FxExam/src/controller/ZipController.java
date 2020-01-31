package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ZipService;
import service.ZipServiceImpl;
import vo.ZipVO;

public class ZipController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<ZipVO, String> zipcode;

	@FXML
	private TableColumn<ZipVO, String> bunji;

	@FXML
	private TableColumn<ZipVO, String> sido;

	@FXML
	private TextField txtbox;

	@FXML
	private Button searchbtn;

	@FXML
	private TableColumn<ZipVO, String> dong;

	@FXML
	private ComboBox<String> combobox;

	@FXML
	private TableColumn<ZipVO, String> gugun;

	@FXML
	private TableView<ZipVO> table;
	
	ZipService  zipservice = ZipServiceImpl.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
		gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
		dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
		bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));
		
		
		combobox.getItems().addAll("동이름", "우편번호");
//		combobox.setValue("동이름");
		
		searchbtn.setOnAction(e ->{
			String search = null;
			Map<String, String> map = new HashMap<>();
			if (combobox.getValue() == null || combobox.getValue().equals("")) {
				//map.put("nullset", "1");
			} else if (combobox.getValue().equals("동이름")) {
				map.put("dongSearch", txtbox.getText());
			} else if (combobox.getValue().equals("우편번호")) {
				map.put("zipSearch", txtbox.getText());
			} else {
				System.out.println("오류발생");
				return;
			}
			System.out.println(map);
			List<ZipVO> list = zipservice.getSearchData(map);
			ObservableList<ZipVO> result = FXCollections.observableArrayList(list);
			table.setItems(result);
		});
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
