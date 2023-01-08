package liverary.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import liverary.Globals;
import liverary.controller.GetAccountByNoController;
import liverary.controller.GetCurrentLoanStatusController;
import liverary.controller.GetLoanRecordsByISBNController;
import liverary.controller.GetLoanRecordsByKeywordController;
import liverary.controller.IsThisReturnNeededPenalty;
import liverary.controller.LendBookController;
import liverary.controller.ReturnBookController;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;
import liverary.vo.LoanByAccountVO;
import liverary.vo.LoanVO;

public class UserMainLayout implements Initializable {
	
	@FXML private Label loanStatusLabel;
	@FXML private Hyperlink detailLink;
	@FXML private Hyperlink editAccountInfoLink;
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private ComboBox<String> bookSerachByCombobox;
	@FXML private TextField bookSearchKeywordTextField;
	@FXML private Button bookSearchBtn;
	
	@FXML private TableView<LoanVO> bookSearchTableView;
	
	@FXML private Button detailInfoBtn;
	
	private String bookSearchByType;
	private LoanVO selectedBook;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// 상단 위젯 초기화
		// 왼쪽
		GetCurrentLoanStatusController controller = new GetCurrentLoanStatusController();
		HashMap<String, Integer> status = controller.exec(Globals.getCurrentSessionNo());
		int total = status.get("TOTAL");
		int penalty = status.get("PENALTY");
		int normal = status.get("NORMAL");
		StringBuffer msgBuff = new StringBuffer();
		msgBuff.append("대출중: " + total + "건 (");
		msgBuff.append("정상: " + normal + "건 ");
		msgBuff.append("연체: " + penalty + "건)");
		loanStatusLabel.setText(msgBuff.toString());
		// 오른쪽
		greetingLabel.setText(Globals.getCurrentSessionName() + "(" + Globals.getCurrentSessionUsername() + ")님 반갑습니다.");
		additionalInfoLabel.setText(Globals.getCurrentSessionPoint() + " 포인트");
		
		// 콤보박스 초기화
		bookSerachByCombobox.getItems().removeAll(bookSerachByCombobox.getItems());
		bookSerachByCombobox.getItems().addAll("ISBN", "표제");
		bookSerachByCombobox.getSelectionModel().select("ISBN");
		bookSearchByType = "ISBN";
		
		// 테이블 뷰 초기화
		TableColumn<LoanVO, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(150);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<LoanVO, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(150);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<LoanVO, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<LoanVO, String> translatorColumn = new TableColumn<>("Translator");
		translatorColumn.setMinWidth(150);
		translatorColumn.setCellValueFactory(new PropertyValueFactory<>("btranslator"));
		
		TableColumn<LoanVO, String> availableColumn = new TableColumn<>("Available");
		availableColumn.setMinWidth(150);
		availableColumn.setCellValueFactory(new PropertyValueFactory<>("available_kor"));
		
		bookSearchTableView.getColumns().addAll(
				isbnColumn, titleColumn, authorColumn, translatorColumn, availableColumn);
		
		bookSearchTableView.setRowFactory(e -> {
			TableRow<LoanVO> row = new TableRow<>();
			
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() > 1) {
					selectedBook = row.getItem();	
					detailInfoBtn.fire();
				} else {
					selectedBook = row.getItem();	
				}
			});
			
			return row;
		});
		
		// 선택 계정 및 책 초기화
		selectedBook = null;
	}
	
	@FXML
	private void handleLogout() {
		Globals.setCurrentSession(null);
		StageManager manager = StageManager.getInstance();
		try {
			manager.switchToWithHide(LayoutsEnum.LoginLayout);
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
		if (query.equals("")) {
			(new Alert(
					AlertType.WARNING, "내용을 입력하세요.")).showAndWait();
			return;
		}
		if (bookSearchByType.equals("ISBN")) {
			GetLoanRecordsByISBNController controller = new GetLoanRecordsByISBNController();
			ObservableList<LoanVO> list = controller.exec(query);
			bookSearchTableView.setItems(list);
			if (list.isEmpty()) {
				(new Alert(
						AlertType.WARNING, "조건에 맞는 자료를 찾을 수 없습니다.")).showAndWait();
			}
		} else if (bookSearchByType.equals("표제")) {
			GetLoanRecordsByKeywordController controller = new GetLoanRecordsByKeywordController();
			ObservableList<LoanVO> list = controller.exec(query);
			bookSearchTableView.setItems(list);
			if (list.isEmpty()) {
				(new Alert(
						AlertType.WARNING, "조건에 맞는 자료를 찾을 수 없습니다.")).showAndWait();
			}
		}
	}
	
	@FXML
	private void handleSearchTextFieldEntered() {
		bookSearchBtn.fire();
	}
	
	@FXML
	private void handleDetailInfoBtn() {
		if (selectedBook == null) {
			(new Alert(
					AlertType.WARNING, "먼저 조회할 자료를 선택해주십시오.")).showAndWait();
			return;
		}

		StringBuffer msgBuff = new StringBuffer();
		msgBuff.append("표제: " + selectedBook.getBtitle() + "\n\n");
		msgBuff.append("저자: " + selectedBook.getBauthor() + "\n\n");
		if (!selectedBook.getBtranslator().equals("")) {			
			msgBuff.append("역자: " + selectedBook.getBtranslator() + "\n\n");
		}
		msgBuff.append("출판사: " + selectedBook.getBpublisher() + "\n\n");
		msgBuff.append("발간일자: " + selectedBook.getBdate() + "\n\n");
		msgBuff.append("페이지 수: " + selectedBook.getBpage() + "\n\n");
		msgBuff.append("딸림자료: " + selectedBook.getBsupplement() + "\n\n");
		msgBuff.append("대출 가능 여부: " + selectedBook.getAvailable_kor() + "\n\n");
		if (!selectedBook.isAvailable()) {
			msgBuff.append("반납 예정일: " + selectedBook.getLduedate());
		}
		
		Alert alert = new Alert(AlertType.INFORMATION, msgBuff.toString());
		alert.setTitle("자료 세부 정보 조회");
		alert.setHeaderText("자료 세부 정보 조회");
		alert.showAndWait();
	}
	
	@FXML
	private void handleDetailLink() {
		Parent modalRoot = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("userHistoryModalFXML.fxml"));
			modalRoot = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setScene(new Scene(modalRoot));
		Platform.runLater(() -> {
			dialog.showAndWait();
		});
	}
	
	@FXML
	private void handleEditAccountInfoLink() {
		Parent modalRoot = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("userEditModalFXML.fxml"));
			modalRoot = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setScene(new Scene(modalRoot));
		Platform.runLater(() -> {
			dialog.showAndWait();
		});
	}

}
