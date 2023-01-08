package liverary.view;

import java.io.IOException;
import java.net.URL;
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
import liverary.controller.GetLoanRecordsByISBNController;
import liverary.controller.GetLoanRecordsByKeywordController;
import liverary.controller.IsThisReturnNeededPenalty;
import liverary.controller.LendBookController;
import liverary.controller.ReturnBookController;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;
import liverary.vo.LoanVO;

public class MainLayout implements Initializable {

	@FXML private VBox rootVBox;
	
	@FXML private Label todayLabel;
	@FXML private Label dueDateLabel;
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private ComboBox<String> bookSerachByCombobox;
	@FXML private TextField bookSearchKeywordTextField;
	@FXML private Button bookSearchBtn;
	
	@FXML private Label currentUserLabel;
	@FXML private Button showSearchAccoutModalBtn;
	
	@FXML private TableView<LoanVO> bookSearchTableView;
	
	@FXML private Button lendBtn;
	@FXML private Button returnBtn;
	
	private Node menuComponent; 
	
	private String bookSearchByType;
	
	private SearchAccountsModal controller;
	private AccountVO selectedAccount;
	private LoanVO selectedBook;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 상단 메뉴 추가
		try {
			menuComponent = FXMLLoader.load(getClass().getResource("menuComponentFXML.fxml"));
			rootVBox.getChildren().add(0, menuComponent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// 상단 위젯 초기화
		// 왼쪽
		todayLabel.setText(DateHelper.todayDateStr());
		dueDateLabel.setText("반납예정일: " + DateHelper.AddDaysToTodayDateStr(7));
		// 오른쪽
		greetingLabel.setText(Globals.getCurrentSessionName() + "(" + Globals.getCurrentSessionUsername() + ")님 반갑습니다.");
		additionalInfoLabel.setText(Globals.getCurrentSessionDepartment() + " | 권한" + Globals.getCurrentSessionLevel());
		
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
				selectedBook = row.getItem();			
			});
			
			return row;
		});
		
		// 선택 계정 및 책 초기화
		selectedAccount = null;
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
	private void handleShowSearchAccoutModalBtn() {
		Parent modalRoot = null;
		controller = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("searchAccountsModalFXML.fxml"));
			modalRoot = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setScene(new Scene(modalRoot));
		Platform.runLater(() -> {
			dialog.showAndWait();
			if (controller.getSelectedAccount() != null) {
				selectedAccount = controller.getSelectedAccount();
				currentUserLabel.setText(selectedAccount.getAname() + "(" + selectedAccount.getAbirth() +")");				
			}
		});
	}
	
	@FXML
	private void handleLendBtn() {
		if (selectedBook == null) {
			(new Alert(
					AlertType.ERROR, "먼저 자료를 선택해주십시오.")).showAndWait();
			return;
		}
		
		if (selectedAccount == null) {
			(new Alert(
					AlertType.ERROR, "먼저 이용자를 선택해주십시오.")).showAndWait();
			return;
		}
		
		if (selectedBook.isAvailable()) {
			selectedBook.setAno(selectedAccount.getAno());
			LendBookController controller = new LendBookController();
			Boolean sucess = controller.exec(selectedBook);
			
			if (sucess) {
				String msg = "대출 처리에 성공하였습니다.\n\n"
						+ "대출 자료: " + selectedBook.getBtitle() + "(" + selectedBook.getBisbn() + ")\n\n"
								+ "이용자: " + selectedAccount.getAname() + "(" + selectedAccount.getAbirth() + ")";
				(new Alert(	AlertType.INFORMATION, msg)).showAndWait();
				bookSearchTableView.setItems(FXCollections.<LoanVO>observableArrayList());
				selectedBook = null;
			} else {
				(new Alert(
						AlertType.ERROR, "대출 처리에 실패하였습니다. 문제가 반복되면 관리자에게 문희하십시오.")).showAndWait();
			}
			
		} else {
			(new Alert(
					AlertType.ERROR, "현재 대출이 불가능한 자료입니다.")).showAndWait();
		}
	}
	
	@FXML
	private void handleReuturnBtn() {
		if (selectedBook == null) {
			(new Alert(
					AlertType.ERROR, "먼저 자료를 선택해주십시오.")).showAndWait();
			return;
		}
		
		if (selectedBook.isAvailable()) {
			(new Alert(
					AlertType.ERROR, "아직 대출되지 않은 자료를 선택하였습니다.")).showAndWait();
			return;
		}
		
		IsThisReturnNeededPenalty lcontroller1 = new IsThisReturnNeededPenalty();
		boolean penalty = lcontroller1.exec(selectedBook);
		if (penalty) {
			GetAccountByNoController acontroller = new GetAccountByNoController();
			AccountVO returnAccount = acontroller.exec(selectedBook.getAno());
			
			String msg = "연체자료 알림\n\n"
					+ "대상 자료: " + selectedBook.getBtitle() + "(" + selectedBook.getBisbn() + ")\n\n"
					+ "정상 반납기일: " + selectedBook.getLduedate() + "\n\n"
					+ "이용자: " + returnAccount.getAname() + "(" + returnAccount.getAbirth() + ")\n\n"
					+ "포인트 감소: " + returnAccount.getApoint() + "->" + (returnAccount.getApoint() - 100);
			Optional<ButtonType> result = new Alert(AlertType.CONFIRMATION, msg).showAndWait();
			if(!result.isPresent()) {
				return;
			} else if(result.get() == ButtonType.OK) {
				//
			} else if(result.get() == ButtonType.CANCEL) {
				return;
			}
			
		} else {
			
		}
		
		ReturnBookController lcontroller2 = new ReturnBookController();
		boolean sucess = lcontroller2.exec(selectedBook);
		
		if (sucess) {
			GetAccountByNoController accountController = new GetAccountByNoController();
			AccountVO returnedAccount = accountController.exec(selectedBook.getAno());
			String msg = "반납 처리에 성공하였습니다.\n\n"
							+ "반납 자료: " + selectedBook.getBtitle() + "(" + selectedBook.getBisbn() + ")\n\n"
									+ "이용자: " + returnedAccount.getAname() + "(" + returnedAccount.getAbirth() + ")";
			(new Alert(	AlertType.INFORMATION, msg)).showAndWait();
			bookSearchTableView.setItems(FXCollections.<LoanVO>observableArrayList());
			selectedBook = null;
		} else {
			(new Alert(
					AlertType.ERROR, "반납 처리에 실패하였습니다. 문제가 반복되면 관리자에게 문희하십시오.")).showAndWait();
		}
	}
}
