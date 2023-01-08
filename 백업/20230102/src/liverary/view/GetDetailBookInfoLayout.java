package liverary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import liverary.controller.GetBooksByISBNController;
import liverary.controller.GetBooksByKeywordController;
import liverary.vo.BookVO;
import liverary.vo.LoanVO;

public class GetDetailBookInfoLayout implements Initializable {

	@FXML private VBox rootVBox;
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private ComboBox<String> bookSerachByCombobox;
	@FXML private TextField bookSearchKeywordTextField;
	@FXML private Button bookSearchBtn;
	
	@FXML private TableView<BookVO> bookSearchTableView;
	
	@FXML private Button showDetailModalBtn;
	@FXML private Button showEditModalBtn;
	
	private Node menuComponent; 
	
	private String bookSearchByType;
	
	private BookVO selectedBook;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		
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
		
		// 테이블 뷰 초기화
		TableColumn<BookVO, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(120);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = new TableColumn<>("표제");
		titleColumn.setMinWidth(250);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<BookVO, String> dateColumn = new TableColumn<>("발간연월");
		dateColumn.setMinWidth(75);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("bdate"));
		
		TableColumn<BookVO, String> authorColumn = new TableColumn<>("저자");
		authorColumn.setMinWidth(75);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, String> translatorColumn = new TableColumn<>("역자");
		translatorColumn.setMinWidth(75);
		translatorColumn.setCellValueFactory(new PropertyValueFactory<>("btranslator"));

		TableColumn<BookVO, String> publisherColumn = new TableColumn<>("출판사");
		publisherColumn.setMinWidth(75);
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("bpublisher"));
		
		bookSearchTableView.getColumns().addAll(
				isbnColumn, titleColumn, dateColumn, authorColumn, translatorColumn, publisherColumn);
		
		bookSearchTableView.setRowFactory(e -> {
			TableRow<BookVO> row = new TableRow<>();
			
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() > 1) {
					selectedBook = row.getItem();	
					showDetailModalBtn.fire();
				} else {
					selectedBook = row.getItem();	
				}
			});
			
			return row;
		});
	}
	
	private void setDataToTableViewByKeyword() {
		GetBooksByKeywordController controller = new GetBooksByKeywordController();
		ObservableList<BookVO> list = controller.exec(bookSearchKeywordTextField.getText());
		if (list.isEmpty()) {
			(new Alert(
					AlertType.WARNING, "조건에 맞는 자료를 찾을 수 없습니다.")).showAndWait();
		} else {
			Platform.runLater(() -> {
				bookSearchTableView.setItems(list);
			});			
		}
	}
	
	private void setDataToTableViewByISBN() {
		GetBooksByISBNController controller = new GetBooksByISBNController();
		ObservableList<BookVO> list = controller.exec(bookSearchKeywordTextField.getText());
		if (list.isEmpty()) {
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
	private void handleShowDetailModal() {
		if (selectedBook == null) {
			(new Alert(
					AlertType.WARNING, "먼저 조회할 자료를 선택해주십시오.")).showAndWait();
			return;
		}

		StringBuffer msgBuff = new StringBuffer();
		msgBuff.append("ISBN: " + selectedBook.getBisbn() + "\n\n");
		msgBuff.append("표제: " + selectedBook.getBtitle() + "\n\n");
		msgBuff.append("주저자: " + selectedBook.getBauthor() + "\n\n");
		if (!selectedBook.getBtranslator().equals("")) {			
			msgBuff.append("역자: " + selectedBook.getBtranslator() + "\n\n");
		}
		msgBuff.append("출판사: " + selectedBook.getBpublisher() + "\n\n");
		msgBuff.append("발간연월: " + selectedBook.getBdate() + "\n\n");
		msgBuff.append("페이지 수: " + selectedBook.getBpage() + "\n\n");
		msgBuff.append("딸림자료: " + selectedBook.getBsupplement() + "\n\n");
		msgBuff.append("입수금액: " + selectedBook.getBprice() + "\n\n");
		Alert alert = new Alert(AlertType.INFORMATION, msgBuff.toString());
		alert.setTitle("자료 세부 정보 조회");
		alert.setHeaderText("자료 세부 정보 조회");
		alert.showAndWait();
	}
	
	@FXML
	private void handleShowEditModalBtn() {
		if (selectedBook == null) {
			(new Alert(
					AlertType.WARNING, "먼저 조회할 자료를 선택해주십시오.")).showAndWait();
			return;
		}
		
		Parent modalRoot = null;
		EditBookInfoModal controller = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("editBookInfoModalFXML.fxml"));
			modalRoot = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.getDataAndSetTableView(selectedBook.getBisbn());
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setScene(new Scene(modalRoot));
		Platform.runLater(() -> {
			dialog.showAndWait();
			bookSearchBtn.fire();
		});
	}

}
