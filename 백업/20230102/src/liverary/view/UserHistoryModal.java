package liverary.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import liverary.Globals;
import liverary.controller.GetLoanBookRecordsByAnoController;
import liverary.vo.LoanVO;

public class UserHistoryModal implements Initializable {

	@FXML private TableView<LoanVO> historyTableView;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle bundle) {		
		// 테이블 뷰 초기화
		TableColumn<LoanVO, String> lentAtColumn = new TableColumn<>("대출일자");
		lentAtColumn.setMinWidth(75);
		lentAtColumn.setCellValueFactory(new PropertyValueFactory<>("lcreatedat"));
		
		TableColumn<LoanVO, String> returnedDateColumn = new TableColumn<>("반납일자");
		returnedDateColumn.setMinWidth(75);
		returnedDateColumn.setCellValueFactory(new PropertyValueFactory<>("lreturnedAt"));
		
		TableColumn<LoanVO, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(75);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<LoanVO, String> titleColumn = new TableColumn<>("표제");
		titleColumn.setMinWidth(75);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<LoanVO, String> authorColumn = new TableColumn<>("저자");
		authorColumn.setMinWidth(75);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<LoanVO, String> publisherColumn = new TableColumn<>("출판사");
		publisherColumn.setMinWidth(75);
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("bpublisher"));
		
		TableColumn<LoanVO, String> statusColumn = new TableColumn<>("반납종류");
		statusColumn.setMinWidth(75);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("available_kor"));
		
		historyTableView.getColumns().addAll(
				lentAtColumn, returnedDateColumn, isbnColumn, titleColumn, authorColumn, publisherColumn, statusColumn);
		
		GetLoanBookRecordsByAnoController controller = new GetLoanBookRecordsByAnoController();
		ObservableList<LoanVO> list = controller.exec(Globals.getCurrentSessionNo());
		historyTableView.setItems(list);
	}
	
}
