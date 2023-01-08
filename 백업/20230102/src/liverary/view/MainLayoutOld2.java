package liverary.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MainLayoutOld2 implements Initializable {
	
	@FXML private VBox mainVbox;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		Node menuComponent = null;
		Node mainComponent = null;
		try {
			menuComponent = FXMLLoader.load(getClass().getResource("menuComponentFXML.fxml"));
			mainComponent = FXMLLoader.load(getClass().getResource("mainComponentFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((VBox) mainComponent).setFillWidth(true);
		mainVbox.getChildren().addAll(menuComponent, mainComponent);
		((VBox) mainVbox).setFillWidth(true);
	}

}
