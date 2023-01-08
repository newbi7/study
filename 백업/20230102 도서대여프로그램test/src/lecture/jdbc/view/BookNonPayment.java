package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookNonPayment extends Stage {

	public BookNonPayment() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlcontroller/BookNonPayment.fxml"));
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}