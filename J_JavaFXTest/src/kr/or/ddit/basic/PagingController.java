package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PagingController implements Initializable{

	@FXML TableView<MemberVO> tableView;
	@FXML TableColumn<MemberVO, String> id;
	@FXML TableColumn<MemberVO, String> name;
	@FXML TableColumn<MemberVO, String> address;
	@FXML Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<MemberVO> allTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		// 전체 테이블 데이터 설정
		allTableData = FXCollections.observableArrayList(); 
		
		for (int j = 1; j < 300; j++) {
			allTableData.add(new MemberVO(j+"", "홍길동", "대전시 중구 대흥동 대덕인재개발원"));
		}
		
		itemsForPage = 5; //한페이지당 보여줄 항목 갯수, 테이블에서보이는갯수는 이것에 + 1개
		int totalPageCount = allTableData.size()%itemsForPage == 0 ? allTableData.size()/itemsForPage : allTableData.size()/itemsForPage + 1;

		pagination.setMaxPageIndicatorCount(5);   // 한번에 보여지는 page indicator
		pagination.setPageCount(totalPageCount); // 전체 페이지수 설정
		pagination.setPadding(new Insets(0));
		pagination.setPrefHeight((itemsForPage*40) + 5);
		pagination.setPageFactory(new Callback<Integer, Node>() {
		
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage; // 각 인덱스별 시작번호
				to = from + itemsForPage; // 테이블의 row갯수
				tableView.setItems(getTableViewData(from, to));
				
				return tableView;
			}
			
			/**
			 * TableView에  채워줄 데이터를 가져오는 메소드
			 * @param from
			 * @param to
			 * @return
			 */
			private ObservableList<MemberVO> getTableViewData(int from, int to) {
				currentPageData = FXCollections.observableArrayList();
				
				int totalSize = allTableData.size();
				for (int i = from; i < to && i <totalSize; i++) {
					currentPageData.add(allTableData.get(i));
				}
				return currentPageData;
			}
		});
	}
}
