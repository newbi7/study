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
import liverary.Globals;
import liverary.controller.GetAccountByNoController;
import liverary.controller.NewAccountController;
import liverary.controller.UpdateAccountController;
import liverary.controller.VerifyNewUsernameController;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;

public class UserEditModal implements Initializable {

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
	
	@FXML private Button editBtn;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		String birthStr = Globals.getCurrentSessionBirth();
		String[] birthStrArr = birthStr.split("-");
		
		nameTextField.setText(Globals.getCurrentSessionName());
		yearTextField.setText(birthStrArr[0]);
		monthsTextField.setText(birthStrArr[1]);
		dayTextField.setText(birthStrArr[2]);
		phoneTextField.setText(Globals.getCurrentSessionPhone());
		emailTextField.setText(Globals.getCurrentSessionEmail());
		addrTextField.setText(Globals.getCurrentSessionAddr());
		usernameTextField.setText(Globals.getCurrentSessionUsername());
	}
	
	@FXML
	private void handleEditBtn() {
		int ano = Globals.getCurrentSessionNo();
		String name = nameTextField.getText();
		String phone = phoneTextField.getText();
		String email = emailTextField.getText();
		String addr = addrTextField.getText();
		String password = passwordTextField.getText();
		String passwordConfirm = passwordConfirmTextField.getText();
		
		if ("".equals(name) || "".equals(phone) || "".equals(email) || "".equals(addr)) {
			(new Alert(
					AlertType.ERROR, "모든 내용을 빠짐없이 입력해주세요.")).showAndWait();
			return;
		}
		
		if (password.equals("") && password.equals("")) {
			password = Globals.getCurrentSessionPassword();
		} else if (!password.equals(passwordConfirm)) {
			(new Alert(
					AlertType.ERROR, "비밀번호란과 비밀번호 확인란의 입력 내용이 다릅니다. 다시 한 번 확인해주세요.")).showAndWait();
			return;
		}

		AccountVO newAccount = new AccountVO(ano, name, null, phone, email, addr, password);
		
		UpdateAccountController controller = new UpdateAccountController();
		boolean success = controller.exec(newAccount);
		
		if (success) {
			(new Alert(
					AlertType.INFORMATION, "회원정보 수정에 성공했습니다. 다시 로그인해주세요.")).showAndWait();
		    Stage stage = (Stage)editBtn.getScene().getWindow();
		    stage.close();
		    StageManager manager = StageManager.getInstance();
		    try {
				manager.switchTo(LayoutsEnum.LoginLayout);
				manager.freeParent(LayoutsEnum.UserMainLayout);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			(new Alert(
					AlertType.ERROR, "회원가입 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		}
	}
	
	

}
