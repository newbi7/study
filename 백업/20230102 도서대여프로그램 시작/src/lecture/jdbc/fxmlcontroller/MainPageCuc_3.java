package lecture.jdbc.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainPageCuc_3 implements Initializable {

	@FXML private Button exitBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("컨트롤러 연결!");
		
		
		exitBtn.setOnAction(e -> {
			System.exit(0);
		});
	}
}
