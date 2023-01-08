package liverary.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class MainLayoutOld implements Initializable {
	
	@FXML private MenuItem goToMainMenuItem;
	@FXML private MenuItem goToRecentLogMenuItem;
	@FXML private MenuItem goToNoReturnedMenuItem;
	@FXML private MenuItem goToAddNewBookMenuItem;
	@FXML private MenuItem goToGetDetailBookInfoMenuItem;
	@FXML private MenuItem goToEditBookInfoMenuItem;
	@FXML private MenuItem goToRegisterStaffAccountMenuItem;
	@FXML private MenuItem goToEditStaffAccountMenuItem;
	
	@FXML private Label todayLabel;
	@FXML private Label dueDateLabel;
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML
	private void handleGoToMainMenuItem() {
		System.out.println("handleGoToMainMenuItem");
	}
	
	@FXML
	private void handleGoToRecentLogMenuItem() {
		System.out.println("handleGoToRecentLogMenuItem");
	}
	
	@FXML
	private void handleGoToNoReturnedMenuItem() {
		System.out.println("handleGoToNoReturnedMenuItem");
	}
	
	@FXML
	private void handleGoToAddNewBookMenuItem() {
		System.out.println("handleGoToAddNewBookMenuItem");
	}
	
	@FXML
	private void handleGoToGetDetailBookInfoMenuItem() {
		System.out.println("handleGoToGetDetailBookInfoMenuItem");
	}
	
	@FXML
	private void handleGoToEditBookInfoMenuItem() {
		System.out.println("handleGoToEditBookInfoMenuItem");
	}
	
	@FXML
	private void handleGoToRegisterStaffAccountMenuItem() {
		System.out.println("handleGoToRegisterStaffAccountMenuItem");
	}
	
	@FXML
	private void handleGoToEditStaffAccountMenuItem() {
		System.out.println("handleGoToEditStaffAccountMenuItem");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		LocalDate date = LocalDate.now();
		LocalDate dueDate = date.plusDays(7);
		todayLabel.setText(date.toString());
		dueDateLabel.setText("반납예정일: " + dueDate.toString());
	}

}
