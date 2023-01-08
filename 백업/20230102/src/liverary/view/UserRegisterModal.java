package liverary.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import liverary.controller.NewAccountController;
import liverary.controller.VerifyNewUsernameController;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;

public class UserRegisterModal implements Initializable {

	@FXML private TextField nameTextField;
	@FXML private TextField yearTextField;
	@FXML private TextField monthsTextField;
	@FXML private TextField dayTextField;
	@FXML private TextField phoneTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField addrTextField;
	@FXML private TextField usernameTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private PasswordField passwordConfirmTextField;
	
	@FXML private Button verifyBtn;
	@FXML private Button registerBtn;
	
	private boolean verified;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		verified = false;
	}
	
	@FXML
	private void handleVerifyBtn() {
		String username = usernameTextField.getText();
		VerifyNewUsernameController controller = new VerifyNewUsernameController();
		boolean success = controller.exec(username);
		if (success) {
			(new Alert(
					AlertType.INFORMATION, username + "은(는) 아이디로 사용 가능합니다.")).showAndWait();
			verified = true;
		} else {
			(new Alert(
					AlertType.ERROR, username + "은(는) 아이디로 사용할 수 없습니다. 다른 아이디를 입력해주세요.")).showAndWait();
			verified = false;
		}
	}
	
	@FXML
	private void handleRegisterBtn() {
		if (!verified) {
			(new Alert(
					AlertType.INFORMATION, "회원가입하기 전에 아이디 중복검사를 진행해주십시오.")).showAndWait();
			return;
		}
		
		String name = nameTextField.getText();
		String year = yearTextField.getText();
		String months = monthsTextField.getText();
		String day = dayTextField.getText();
		String phone = phoneTextField.getText();
		String email = emailTextField.getText();
		String addr = addrTextField.getText();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String passwordConfirm = passwordConfirmTextField.getText();
		
		if ("".equals(name) || "".equals(year) || "".equals(months) ||
				"".equals(day) || 	"".equals(phone) || "".equals(email) ||
				"".equals(addr) || "".equals(username) || "".equals(password) || "".equals(passwordConfirm)) {
			(new Alert(
					AlertType.ERROR, "모든 내용을 빠짐없이 입력해주세요.")).showAndWait();
			return;
		}
		
		if (!password.equals(passwordConfirm)) {
			(new Alert(
					AlertType.ERROR, "비밀번호란과 비밀번호 확인란의 입력 내용이 다릅니다. 다시 한 번 확인해주세요.")).showAndWait();
			return;
		}
		
		String birth = year + "-" + months + "-" + day;

		AccountVO newAccount = new AccountVO(name, birth, DateHelper.todayDateStr(), phone,
												email, addr, 0, 0, username, password);
		
		NewAccountController controller = new NewAccountController();
		boolean success = controller.exec(newAccount);
		
		if (success) {
			(new Alert(
					AlertType.INFORMATION, "회원가입에 성공했습니다.")).showAndWait();
		    Stage stage = (Stage)registerBtn.getScene().getWindow();
		    stage.close();
		} else {
			(new Alert(
					AlertType.ERROR, "회원가입 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		}
	}
	
	

}
