package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PeopleSearch extends Stage {

	public PeopleSearch() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlcontroller/PeopleSearch.fxml"));
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}