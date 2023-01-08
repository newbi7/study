package lecture.jdbc.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lecture.jdbc.fxmlcontroller.MainPageAdc_2;

public class MainPageAd_2 extends Stage {
	
	public MainPageAd_2() {
	}

	public MainPageAd_2(String arg) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlcontroller/MainPageAd_2.fxml"));
			Parent root = loader.load();
			MainPageAdc_2 controller = loader.getController();
			controller.setArgAndRender(arg);
			Scene scene = new Scene(root);
			this.setScene(scene);
			this.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}