package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lecture.jdbc.fxmlcontroller.PeopleInfdel_insc;

public class PeopleInfdel_ins extends Stage {
	
	public PeopleInfdel_ins() {
	}
	
	public PeopleInfdel_ins(String arg) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlcontroller/PeopleInfdel_ins.fxml"));
			Parent root = loader.load();
			PeopleInfdel_insc controller = loader.getController();
			controller.setArgAndRender(arg);
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}