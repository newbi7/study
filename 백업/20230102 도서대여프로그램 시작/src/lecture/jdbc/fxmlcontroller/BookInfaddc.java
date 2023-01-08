package lecture.jdbc.fxmlcontroller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lecture.jdbc.service.BookService;

public class BookInfaddc implements Initializable {
	
	@FXML private TextField bisbntextfield;
	@FXML private TextField btitletextfield;
	@FXML private TextField bdatetextfield;
	@FXML private TextField bpagetextfield;
	@FXML private TextField bpricetextfield;
	@FXML private TextField bautortextfield;
	@FXML private TextField btranslatortextfield;
	@FXML private TextField bsupplementtextfield;
	@FXML private TextField bpublishertextfield;
	@FXML private TextField bimgurltextfield;
	
	@FXML private Button bookinsertbutton;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		bookinsertbutton.setOnAction(e -> {
			BookService service = new BookService(); 
			if (bisbntextfield.getText().equals("") || btitletextfield.getText().equals("")
					|| bdatetextfield.getText().equals("") || bpagetextfield.getText().equals("")
					|| bpricetextfield.getText().equals("")|| bautortextfield.getText().equals("")
					|| btranslatortextfield.getText().equals("") || bsupplementtextfield.getText().equals("")
					|| bpublishertextfield.getText().equals("")|| bimgurltextfield.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("빈칸이 있습니다.");
				alert.showAndWait();
			} else {
				service.Bookinfadd(bisbntextfield.getText(), btitletextfield.getText(), 
						bdatetextfield.getText(), bpagetextfield.getText(),
						bpricetextfield.getText(), bautortextfield.getText(),
						btranslatortextfield.getText(), bsupplementtextfield.getText(),
						bpublishertextfield.getText(), bimgurltextfield.getText());

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("성공");
				alert.setHeaderText("책을 추가였습니다.");
				alert.showAndWait();
				((Button)bookinsertbutton).getScene().getWindow().hide();
			}
		});
		
		
	}
}
