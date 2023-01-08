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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import liverary.Globals;
import liverary.controller.GetAccountByUsernameController;
import liverary.vo.AccountVO;

public class LoginLayout implements Initializable {
	
	@FXML private TextField usernameTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private Button loginBtn;
	@FXML private Button registerBtn;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
	}
	
	@FXML
	private void handleLoginBtn() {
		String inputUsername = usernameTextField.getText();
		String inputPassword = passwordTextField.getText();
		if (inputUsername.equals("") || inputPassword.equals("")) {
			(new Alert(
					AlertType.ERROR, "로그인에 실패했습니다. 아이디와 비밀번호를 다시 한 번 확인해주십시오.")).showAndWait();
			return;
		}
		
		GetAccountByUsernameController controller = new GetAccountByUsernameController();
		AccountVO account = controller.exec(inputUsername);
		
		if (account == null) {
			(new Alert(
					AlertType.ERROR, "로그인에 실패했습니다. 아이디와 비밀번호를 다시 한 번 확인해주십시오.")).showAndWait();
			return;
		}
		
		if (inputPassword.equals(account.getApassword())) {
			Globals.setCurrentSession(account);
			StageManager manager = StageManager.getInstance();
			passwordTextField.clear();
			try {
				if (account.getAlevel() > 0) {
					manager.switchToWithHide(LayoutsEnum.MainLayout);					
				} else {
					manager.switchToWithHide(LayoutsEnum.UserMainLayout);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			(new Alert(
					AlertType.ERROR, "로그인에 실패했습니다. 아이디와 비밀번호를 다시 한 번 확인해주십시오.")).showAndWait();
		}
	}
	
	@FXML
	private void usernameTextFieldEntered() {
		loginBtn.fire();
	}
	
	@FXML
	private void passwordTextFieldEntered() {
		loginBtn.fire();
	}
	
	@FXML
	private void handleRegisterBtn() {
		Parent modalRoot = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("userRegisterModalFXML.fxml"));
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
