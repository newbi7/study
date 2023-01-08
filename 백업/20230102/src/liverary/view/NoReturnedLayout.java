package liverary.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import liverary.Globals;
import liverary.controller.GetAccountByNoController;
import liverary.controller.GetLoanBookRecordsByAnoController;
import liverary.controller.GetLoanBookRecordsByISBNController;
import liverary.controller.GetLoanBookRecordsByKeywordController;
import liverary.controller.GetLoanRecordsByISBNController;
import liverary.controller.GetLoanRecordsByKeywordController;
import liverary.controller.GetNoReturnedBookRecordsByISBNController;
import liverary.controller.GetNoReturnedBookRecordsByKeywordController;
import liverary.controller.IsThisReturnNeededPenalty;
import liverary.controller.LendBookController;
import liverary.controller.ReturnBookController;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;
import liverary.vo.LoanVO;

public class NoReturnedLayout implements Initializable {

	@FXML private VBox rootVBox;
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private ComboBox<String> bookSerachByCombobox;
	@FXML private TextField bookSearchKeywordTextField;
	@FXML private Button bookSearchBtn;
	
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML private TableView<LoanVO> bookSearchTableView;
	
	private Node menuComponent; 
	
	private String bookSearchByType;
	private String startDate;
	private String endDate;
	
	private boolean startup;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		startup = true;
		
		// 상단 메뉴 추가
		try {
			menuComponent = FXMLLoader.load(getClass().getResource("menuComponentFXML.fxml"));
			rootVBox.getChildren().add(0, menuComponent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// 상단 위젯 초기화
		greetingLabel.setText(Globals.getCurrentSessionName() + "(" + Globals.getCurrentSessionUsername() + ")님 반갑습니다.");
		additionalInfoLabel.setText(Globals.getCurrentSessionDepartment() + " | 권한" + Globals.getCurrentSessionLevel());
		
		// 콤보박스 초기화
		bookSerachByCombobox.getItems().removeAll(bookSerachByCombobox.getItems());
		bookSerachByCombobox.getItems().addAll("ISBN", "표제");
		bookSerachByCombobox.getSelectionModel().select("표제");
		bookSearchByType = "표제";
		
		// 데이트피커 초기화
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		startDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		endDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		
		// 테이블 뷰 초기화
		// 테이블 뷰 초기화
		TableColumn<LoanVO, String> lentAtColumn = new TableColumn<>("대출일자");
		lentAtColumn.setMinWidth(75);
		lentAtColumn.setCellValueFactory(new PropertyValueFactory<>("lcreatedat"));
		
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
		
		TableColumn<LoanVO, String> returnedDateColumn = new TableColumn<>("반납일자");
		returnedDateColumn.setMinWidth(75);
		returnedDateColumn.setCellValueFactory(new PropertyValueFactory<>("lreturnedAt"));
		
		TableColumn<LoanVO, String> statusColumn = new TableColumn<>("반납종류");
		statusColumn.setMinWidth(110);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("available_kor"));
		
		bookSearchTableView.getColumns().addAll(
				lentAtColumn, isbnColumn, titleColumn, authorColumn, publisherColumn, returnedDateColumn, statusColumn);
		
		bookSearchBtn.fire();
		
		startup = false;
	}
	
	private void setDataToTableViewByKeyword() {
		GetNoReturnedBookRecordsByKeywordController controller = new GetNoReturnedBookRecordsByKeywordController();
		ObservableList<LoanVO> list = controller.exec(bookSearchKeywordTextField.getText(), startDate, endDate);
		if (list.isEmpty() && !startup) {
			(new Alert(
					AlertType.WARNING, "조건에 맞는 자료를 찾을 수 없습니다.")).showAndWait();
		} else {
			Platform.runLater(() -> {
				bookSearchTableView.setItems(list);
			});			
		}
	}
	
	private void setDataToTableViewByISBN() {
		GetNoReturnedBookRecordsByISBNController controller = new GetNoReturnedBookRecordsByISBNController();
		ObservableList<LoanVO> list = controller.exec(bookSearchKeywordTextField.getText(), startDate, endDate);
		if (list.isEmpty() && !startup) {
			(new Alert(
					AlertType.WARNING, "조건에 맞는 자료를 찾을 수 없습니다.")).showAndWait();
		} else {
			Platform.runLater(() -> {
				bookSearchTableView.setItems(list);
			});			
		}
	}
	
	@FXML
	private void handleLogout() {
		Globals.setCurrentSession(null);
		StageManager manager = StageManager.getInstance();
		try {
			manager.switchTo(LayoutsEnum.LoginLayout);
			manager.freeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void hadleCombobox() {
		bookSearchByType = bookSerachByCombobox.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	private void handleSearchBtn() {
		String query = bookSearchKeywordTextField.getText();
		
		if (bookSearchByType.equals("ISBN")) {
			setDataToTableViewByISBN();
		} else if (bookSearchByType.equals("표제")) {
			setDataToTableViewByKeyword();
		}
	}
	
	@FXML
	private void handleSearchTextFieldEntered() {
		bookSearchBtn.fire();
	}
	
	@FXML
	private void handleStartDatePicker() {
		startDate = startDatePicker.getValue().toString();
		bookSearchBtn.fire();
	}
	
	@FXML
	private void handleEndDatePicker() {
		endDate = endDatePicker.getValue().toString();
		bookSearchBtn.fire();
	}
}
