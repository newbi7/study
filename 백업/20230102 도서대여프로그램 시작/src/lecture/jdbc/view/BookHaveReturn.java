package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lecture.jdbc.fxmlcontroller.BookHaveReturnc;
import lecture.jdbc.fxmlcontroller.PeopleInfdel_insc;

public class BookHaveReturn extends Stage {
	
	public BookHaveReturn() {
	}

	public BookHaveReturn(String arg) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlcontroller/BookHaveReturn.fxml"));
			Parent root = loader.load();
			BookHaveReturnc controller = loader.getController();
			controller.setArgAndRender(arg);
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}