package liverary.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class MenuComponent implements Initializable {
	
	@FXML private MenuItem goToMainMenuItem;
	@FXML private MenuItem goToRecentLogMenuItem;
	@FXML private MenuItem goToNoReturnedMenuItem;
	@FXML private MenuItem goToAddNewBookMenuItem;
	@FXML private MenuItem goToGetDetailBookInfoMenuItem;
	@FXML private MenuItem goToRegisterStaffAccountMenuItem;
	@FXML private MenuItem goToEditStaffAccountMenuItem;
	
	@FXML private Label todayLabel;
	@FXML private Label dueDateLabel;
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	private StageManager manager;
	
	@FXML
	private void handleGoToMainMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.MainLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToRecentLogMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.RecentLogLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToNoReturnedMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.NoReturnedLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToAddNewBookMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.AddNewBookLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToGetDetailBookInfoMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.GetDetailBookInfoLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToRegisterStaffAccountMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.RegisterStaffAccountLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGoToEditStaffAccountMenuItem() {
		try {
			manager.switchTo(LayoutsEnum.GetDetailStaffAccountLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle bundlde) {
		manager = StageManager.getInstance();
	}

}
