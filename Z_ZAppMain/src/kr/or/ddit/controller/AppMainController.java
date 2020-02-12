package kr.or.ddit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.gradeService;
import kr.or.ddit.service.gradeServiceImpl;
import kr.or.ddit.vo.GradeVO;

public class AppMainController {

	gradeService gService = gradeServiceImpl.getInstance();
	List<GradeVO> GvList = gService.getData();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button getBarChart;

	@FXML
	private Button insertButton;

	@FXML
	private TableColumn<GradeVO, String> name;

	@FXML
	private TableColumn<GradeVO, Integer> math;

	@FXML
	private BorderPane table;

	@FXML
	private TableView<GradeVO> tb;

	@FXML
	private TableColumn<GradeVO, Integer> kor;

	@FXML
	private TableColumn<GradeVO, Integer> eng;

	@FXML
	void clickInsert(ActionEvent event) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		dialog.initModality(Modality.APPLICATION_MODAL);

		// 4. 자식창이 나타날 컨테이너 객체 생성
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/utill2/insertScoreDialog.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
		Pane pane = (Pane) parent.lookup("#pane");
		TextField tname = (TextField) parent.lookup("#tName");
		TextField tkor = (TextField) parent.lookup("#tKor");
		TextField tmath = (TextField) parent.lookup("#tMath");
		TextField teng = (TextField) parent.lookup("#tEng");
		Button save = (Button) parent.lookup("#save");
		Button cancel = (Button) parent.lookup("#cancel");

		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정
		dialog.show();

		
		save.setOnAction(e -> {
			// 얻은 데이터를 DB로 전송
			GradeVO gv = new GradeVO();
			gv.setName(tname.getText());
			gv.setKor(Integer.parseInt(tkor.getText()));
			gv.setMath(Integer.parseInt(tmath.getText()));
			gv.setEng(Integer.parseInt(teng.getText()));
			gService.insertData(gv);
			
			// 테이블에 데이터 추가
			tb.getItems().add(gv);

			dialog.close();
			
		});

		cancel.setOnAction(e -> {
			dialog.close();
		});

	}

	@FXML
	void clickBarChart(ActionEvent event) {
		Stage dialog = new Stage(StageStyle.UTILITY);

		dialog.initModality(Modality.APPLICATION_MODAL);

		// 4. 자식창이 나타날 컨테이너 객체 생성
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/utill2/barChart.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
		BarChart<String, Number> barchar = (BarChart<String, Number>) parent.lookup("#bc");

		// 데이터를 추가하기위해서는 series를 추가해줘야한다.
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		ser1.setName("국어");
		for (int i = 0; i < GvList.size(); i++) {
			ser1.getData().add(new Data<String, Number>(GvList.get(i).getName(), GvList.get(i).getKor()));
		}

		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		ser2.setName("수학");
		for (int i = 0; i < GvList.size(); i++) {
			ser2.getData().add(new Data<String, Number>(GvList.get(i).getName(), GvList.get(i).getMath()));
		}

		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
		ser3.setName("영어");
		for (int i = 0; i < GvList.size(); i++) {
			ser3.getData().add(new Data<String, Number>(GvList.get(i).getName(), GvList.get(i).getEng()));
		}
		barchar.getData().addAll(ser1, ser2, ser3);

		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정
		dialog.show();

		// 모달창 닫기
		Button btn = (Button) parent.lookup("#close");
		btn.setOnAction(e -> {
			dialog.close();
		});
	}

	@FXML
	void selectRow(MouseEvent event) {
		// Pie 그래프 생성
		Stage dialog2 = new Stage(StageStyle.UTILITY);

		dialog2.initModality(Modality.APPLICATION_MODAL);

		// 4. 자식창이 나타날 컨테이너 객체 생성
		Parent parent2 = null;
		try {
			parent2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/utill2/pieChart.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		PieChart pieChart = (PieChart) parent2.lookup("#pieChart");
		// 차트가 나타날 데이터 구성하기

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("국어", GvList.get(tb.getSelectionModel().getSelectedIndex()).getKor()),
				new PieChart.Data("수학", GvList.get(tb.getSelectionModel().getSelectedIndex()).getMath()),
				new PieChart.Data("영어", GvList.get(tb.getSelectionModel().getSelectedIndex()).getEng()));

		pieChart.setTitle("파일 그래프");
		pieChart.setLabelLineLength(50);
		pieChart.setLegendSide(Side.RIGHT); // 범례가 나타날 위치
		pieChart.setData(pieChartData); // 데이터 설정

		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene2 = new Scene(parent2);

		// 6. Stage객체에 Scene객체 추가
		dialog2.setScene(scene2);
		dialog2.setResizable(false); // 크기 고정
		dialog2.show();

		// 모달창 닫기
		Button btn2 = (Button) parent2.lookup("#closeButton");
		btn2.setOnAction(e -> {
			dialog2.close();
		});
	}

	@FXML
	void initialize() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kor.setCellValueFactory(new PropertyValueFactory<>("kor"));
		math.setCellValueFactory(new PropertyValueFactory<>("math"));
		eng.setCellValueFactory(new PropertyValueFactory<>("eng"));

		ObservableList<GradeVO> list = FXCollections.observableArrayList(GvList);
		tb.setItems(list);

	}
}
