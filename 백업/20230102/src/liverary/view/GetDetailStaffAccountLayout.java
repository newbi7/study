package liverary.view;

import java.io.IOException;
import java.net.URL;
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
import liverary.controller.GetStaffAccountByUsernameController;
import liverary.controller.GetStaffAccountsByNameController;
import liverary.vo.AccountVO;

public class GetDetailStaffAccountLayout implements Initializable {

	@FXML private VBox rootVBox;
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private ComboBox<String> searchByComboBox;
	@FXML private TextField searchKeywordTextField;
	@FXML private Button searchBtn;
	
	@FXML private TableView<AccountVO> searchTableView;
	
	@FXML private Button showEditModalBtn;
	
	private Node menuComponent; 
	
	private String searchBy;
	
	private AccountVO selectedAccount;
	
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
		searchByComboBox.getItems().removeAll(searchByComboBox.getItems());
		searchByComboBox.getItems().addAll("아이디", "이름");
		searchByComboBox.getSelectionModel().select("아이디");
		searchBy = "아이디";
		
		TableColumn<AccountVO, Integer> noColumn = new TableColumn<>("등록번호");
		noColumn.setMinWidth(20);
		noColumn.setCellValueFactory(new PropertyValueFactory<>("ano"));
		
		TableColumn<AccountVO, String> useridColumn = new TableColumn<>("아이디");
		useridColumn.setMinWidth(150);
		useridColumn.setCellValueFactory(new PropertyValueFactory<>("ausername"));
		
		TableColumn<AccountVO, String> nameColumn = new TableColumn<>("이름");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("aname"));
		
		TableColumn<AccountVO, String> birthColumn = new TableColumn<>("생년월일");
		birthColumn.setMinWidth(150);
		birthColumn.setCellValueFactory(new PropertyValueFactory<>("abirth"));
		
		TableColumn<AccountVO, String> phoneColumn = new TableColumn<>("연락처");
		phoneColumn.setMinWidth(150);
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("aphone"));
		
		TableColumn<AccountVO, String> emailColumn = new TableColumn<>("이메일");
		emailColumn.setMinWidth(150);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("aemail"));
		
		TableColumn<AccountVO, String> enteredDateColumn = new TableColumn<>("입사일");
		enteredDateColumn.setMinWidth(150);
		enteredDateColumn.setCellValueFactory(new PropertyValueFactory<>("acreatedAt"));
		
		TableColumn<AccountVO, String> locationColumn = new TableColumn<>("거주지");
		locationColumn.setMinWidth(150);
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("aaddr"));
		
		TableColumn<AccountVO, String> departmentColumn = new TableColumn<>("부서");
		departmentColumn.setMinWidth(150);
		departmentColumn.setCellValueFactory(new PropertyValueFactory<>("adepartment"));
		
		TableColumn<AccountVO, String> levelColumn = new TableColumn<>("권한");
		levelColumn.setMinWidth(150);
		levelColumn.setCellValueFactory(new PropertyValueFactory<>("alevel"));
		searchTableView.getColumns().addAll(
				noColumn, useridColumn, nameColumn, birthColumn, phoneColumn,
				emailColumn, enteredDateColumn, locationColumn, departmentColumn, levelColumn);
		
		searchTableView.setRowFactory(e -> {
			TableRow<AccountVO> row = new TableRow<>();
			
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					AccountVO account = row.getItem();
					selectedAccount = account;
				}
			});
			return row;
		});
		
		selectedAccount = null;
	}
	
	private void setDataToTableViewByName() {
		GetStaffAccountsByNameController controller = new GetStaffAccountsByNameController();
		ObservableList<AccountVO> list = controller.exec(searchKeywordTextField.getText());
		if (list.isEmpty()) {
			(new Alert(
					AlertType.WARNING, "조건에 맞는 계정을 찾을 수 없습니다.")).showAndWait();
		} else {
			Platform.runLater(() -> {
				searchTableView.setItems(list);
			});			
		}
	}
	
	private void setDataToTableViewByUsername() {
		GetStaffAccountByUsernameController controller = new GetStaffAccountByUsernameController();
		AccountVO account = controller.exec(searchKeywordTextField.getText());

		
		if (account == null) {
			(new Alert(
					AlertType.WARNING, "조건에 맞는 계정을 찾을 수 없습니다.")).showAndWait();
		} else {
			Platform.runLater(() -> {
				ObservableList<AccountVO> list = FXCollections.observableArrayList();
				list.add(account);
				searchTableView.setItems(list);
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
		searchBy = searchByComboBox.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	private void handleSearchBtn() {
		String query = searchKeywordTextField.getText();
		
		if (searchBy.equals("아이디")) {
			setDataToTableViewByUsername();
		} else if (searchBy.equals("이름")) {
			setDataToTableViewByName();
		}
	}
	
	@FXML
	private void handleSearchTextFieldEntered() {
		searchBtn.fire();
	}
	
	@FXML
	private void handleShowEditModalBtn() {
		if (selectedAccount == null) {
			(new Alert(
					AlertType.WARNING, "먼저 조회할 자료를 선택해주십시오.")).showAndWait();
			return;
		}
		
		Parent modalRoot = null;
		EditStaffAccountModal controller = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("editStaffAccountModalFXML.fxml"));
			modalRoot = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}

		controller.getDataAndSetTableView(selectedAccount.getAno());
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.setScene(new Scene(modalRoot));
		Platform.runLater(() -> {
			dialog.showAndWait();
			searchBtn.fire();
		});
	}
}
