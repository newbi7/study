package liverary.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import liverary.controller.DeleteAccountController;
import liverary.controller.GetAccountByNoController;
import liverary.controller.UpdateAccountController;
import liverary.vo.AccountVO;

public class EditStaffAccountModal implements Initializable {
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private TextField nameTextField;
	@FXML private TextField yearTextField;
	@FXML private TextField monthsTextField;
	@FXML private TextField dayTextField;
	@FXML private TextField phoneTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField addrTextField;
	@FXML private TextField enterYearTextField;
	@FXML private TextField enterMonthsTextField;
	@FXML private TextField enterDayTextField;
	@FXML private TextField departmentTextField;
	@FXML private TextField usernameTextField;
	@FXML private TextField levelTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private PasswordField passwordConfirmTextField;
	
	@FXML private Button editBtn;
	@FXML private Button deleteBtn;
	
	private int ano;
	private int point;
	private String username;
	private String originalPassword;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
	}
	
	public void getDataAndSetTableView(int ano) {
		GetAccountByNoController controller = new GetAccountByNoController();
		AccountVO account = controller.exec(ano);
		
		String[] birthStrArr = account.getAbirth().split("-");
		String birthYear = birthStrArr[0];
		String birthMonth = birthStrArr[1];
		String birthDay = birthStrArr[2];
		
		String[] enteredDateStrArr = account.getAcreatedAt().split("-");
		String enterYear = enteredDateStrArr[0];
		String enterMonth = enteredDateStrArr[1];
		String enterDay = enteredDateStrArr[2];
		
		nameTextField.setText(account.getAname());
		yearTextField.setText(birthYear);
		monthsTextField.setText(birthMonth);
		dayTextField.setText(birthDay);
		phoneTextField.setText(account.getAphone());
		emailTextField.setText(account.getAemail());
		addrTextField.setText(account.getAaddr());
		enterYearTextField.setText(enterYear);
		enterMonthsTextField.setText(enterMonth);
		enterDayTextField.setText(enterDay);
		departmentTextField.setText(account.getAdepartment());
		usernameTextField.setText(account.getAusername());
		levelTextField.setText(String.valueOf(account.getAlevel()));
		
		this.ano = ano;
		point = account.getApoint();
		username = account.getAusername();
		originalPassword = account.getApassword();
	}
	
	@FXML
	private void handleEditBtn(ActionEvent e) {
		String name = nameTextField.getText();
		String year = yearTextField.getText();
		String months = monthsTextField.getText();
		String day = dayTextField.getText();
		String enterYear = yearTextField.getText();
		String enterMonths = monthsTextField.getText();
		String enterDay = dayTextField.getText();
		String department = departmentTextField.getText();
		String phone = phoneTextField.getText();
		String email = emailTextField.getText();
		String addr = addrTextField.getText();
		String password = passwordTextField.getText();
		String passwordConfirm = passwordConfirmTextField.getText();
		String levelStr = levelTextField.getText();
		
		String birth = year + "-" + months + "-" + day;
		String enteredDate = enterYear + "-" + enterMonths + "-" + enterDay;
		int level = 1;
		try {
			level = Integer.parseInt(levelTextField.getText());			
		} catch (NumberFormatException exception) {
			(new Alert(
					AlertType.ERROR, "권한은 1 또는 2만 지정할 수 있습니다.")).showAndWait();
			return;
		}
		
		if ("".equals(name) || "".equals(year) || "".equals(months) ||
				"".equals(day) || 	"".equals(phone) || "".equals(email) ||
				"".equals(addr) || "".equals(username) || 
				"".equals(enterYear) || "".equals(enterMonths) || "".equals(enterDay) ||
				"".equals(department) || "".equals(levelStr)) {
			(new Alert(
					AlertType.ERROR, "모든 내용을 빠짐없이 입력해주세요.")).showAndWait();
			return;
		}
		
		if (level < 1 || level > 2) {
			(new Alert(
					AlertType.ERROR, "권한은 1 또는 2만 지정할 수 있습니다.")).showAndWait();
			return;
		}
		
		if (password.equals("") && password.equals("")) {
			password = originalPassword;
		} else if (!password.equals(passwordConfirm)) {
			(new Alert(
					AlertType.ERROR, "비밀번호란과 비밀번호 확인란의 입력 내용이 다릅니다. 다시 한 번 확인해주세요.")).showAndWait();
			return;
		}

		AccountVO newAccount = new AccountVO(ano, name, department, birth, enteredDate, phone,
												email, addr, point, level, username, password);
		
		UpdateAccountController controller = new UpdateAccountController();
		boolean success = controller.exec(newAccount);
		
		if (success) {
			(new Alert(
					AlertType.INFORMATION, "계정 정보 수정에 성공했습니다.")).showAndWait();
			
			Node node = (Node) e.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    thisStage.close();
		} else {
			(new Alert(
					AlertType.ERROR, "계정 정보 수정 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		}
	}
	
	@FXML
	private void handleDeleteBtn(ActionEvent e) {
		Alert alert = 
		        new Alert(AlertType.WARNING, 
		            "정말로 이 계정을 삭제하시겠습니까? 이 작업은 돌이킬 수 없습니다.",
		             ButtonType.OK, 
		             ButtonType.CANCEL);
		alert.setTitle("항목 삭제 경고");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.CANCEL) {
		    return;
		}
		
		DeleteAccountController controller = new DeleteAccountController();
		int success = controller.exec(ano);
		
		if (success == 1) {
			(new Alert(
					AlertType.INFORMATION, "성공적으로 항목을 삭제했습니다.")).showAndWait();
			
			Node node = (Node) e.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    thisStage.close();
		} else if (success == -1) {
			(new Alert(
					AlertType.ERROR, "삭제 작업 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		} else if (success == -2) {
			(new Alert(
					AlertType.ERROR, "미납도서가 있어 삭제할 수 없습니다.")).showAndWait();
		} else {
			(new Alert(
					AlertType.ERROR, "알 수 없는 오류가 발생했습니다.")).showAndWait();
		}
	}
}
