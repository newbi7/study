package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lecture.jdbc.fxmlcontroller.BookInfdelc;

public class BookInfdel_ins extends Stage {
	
	public BookInfdel_ins() {
	}
	
	public BookInfdel_ins(String arg) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlcontroller/BookInfdel_ins.fxml"));
			Parent root = loader.load();
			BookInfdelc controller = loader.getController();
			controller.setArgAndRender(arg);
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}