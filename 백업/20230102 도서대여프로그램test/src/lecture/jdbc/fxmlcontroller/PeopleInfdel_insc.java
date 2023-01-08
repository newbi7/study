package lecture.jdbc.fxmlcontroller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lecture.jdbc.service.PeopleService;
import lecture.jdbc.vo.BookVO;
import lecture.jdbc.vo.PeopleVO;

public class PeopleInfdel_insc implements Initializable {

	MainPageAdc_2 mainPageAdc_2 = new MainPageAdc_2();
	String idtext;
	String exidtext;

	@FXML private TextField idtextfield;
	@FXML private TextField nidtextfield;
	@FXML private TextField pwtextfield;
	@FXML private TextField npwtextfield;
	@FXML private TextField nametextfield;
	@FXML private TextField nnametextfield;
	@FXML private TextField ssnumbertextfield;
	@FXML private TextField nssnumbertextfield;
	@FXML private TextField pphonetextfield;
	@FXML private TextField npphonetextfield;
	@FXML private Button peopledeletebutton;
	@FXML private Button peopleinfinsbutton;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		peopledeletebutton.setOnAction(e -> {

			PeopleService service = new PeopleService(); 
			service.DeletePeople(idtext);
			Alert alert = new Alert(AlertType.INFORMATION); 
			alert.setTitle("성공");
			alert.setHeaderText("회원탈퇴하셨습니다."); alert.showAndWait();

			System.exit(0);

		});

		peopleinfinsbutton.setOnAction(e -> {
			PeopleService service = new PeopleService(); 
			if (nidtextfield.getText().equals("") || npwtextfield.getText().equals("")
					|| nnametextfield.getText().equals("") || nssnumbertextfield.getText().equals("")
					|| npphonetextfield.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("빈칸이 있습니다.");
				alert.showAndWait();
			} else {
				service.Peopleinfins(nidtextfield.getText(), npwtextfield.getText(), 
						nnametextfield.getText(), nssnumbertextfield.getText(),
						npphonetextfield.getText(), nidtextfield.getText());

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("성공");
				alert.setHeaderText("정보수정하였습니다.");
				alert.showAndWait();
				((Button)peopleinfinsbutton).getScene().getWindow().hide();
			}
		});

	}

	public void setArgAndRender(String idtext) {
		this.idtext = idtext;
		idtextfield.setText(idtext);

		PeopleService service = new PeopleService();
		PeopleVO person = service.Peopleinf(idtext);
		pwtextfield.setText(person.getPpw());
		nametextfield.setText(person.getPname());
		ssnumbertextfield.setText(person.getPssnumber());
		pphonetextfield.setText(person.getPphone());
		nidtextfield.setText(idtext);


	}
}
