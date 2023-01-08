package liverary.view;

import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class FXMLProperty {
	private URL url;
	private int prefWidth;
	private int prefHeight;
	private String title;
	
	public FXMLProperty() {
	}
	
	public FXMLProperty(URL url, int prefWidth, int prefHeight, String title) {
		super();
		this.url = url;
		this.prefWidth = prefWidth;
		this.prefHeight = prefHeight;
		this.title = title;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public int getPrefWidth() {
		return prefWidth;
	}

	public void setPrefWidth(int prefWidth) {
		this.prefWidth = prefWidth;
	}

	public int getPrefHeight() {
		return prefHeight;
	}

	public void setPrefHeight(int prefHeight) {
		this.prefHeight = prefHeight;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

public class StageManager {
	private Stage stage;
	private Scene scene;
	private HashMap<LayoutsEnum, FXMLProperty> fxmlMap;
	private HashMap<LayoutsEnum, Parent> parentMap;
	private static StageManager instance;
	
	public StageManager() {
		fxmlMap = new HashMap<LayoutsEnum, FXMLProperty>();
		parentMap = new HashMap<LayoutsEnum, Parent>();
		initFXMLs();
	}
	
	public StageManager(Stage stage) {
		this();		
		this.stage = stage;
	}
	
	public static StageManager getInstance() {
		
		if (instance == null) {
			instance = new StageManager();
		}

		return instance;
	}
	
	public static StageManager getInstance(Stage stage) {
		
		if (instance == null) {
			instance = new StageManager(stage);
		}

		return instance;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private void putFXMLProperty(LayoutsEnum key, FXMLProperty fxmlProperty) {
		this.fxmlMap.put(key, fxmlProperty);
	}
	
	private FXMLProperty getFXMLProperty(LayoutsEnum key) {
		return this.fxmlMap.get(key);
	}
	
	private void initFXMLs() {
		putFXMLProperty(LayoutsEnum.LoginLayout, new FXMLProperty(getClass().getResource("loginLayoutFXML.fxml"),
							400, 230, "로그인"));
		putFXMLProperty(LayoutsEnum.UserMainLayout, new FXMLProperty(getClass().getResource("userMainLayoutFXML.fxml"),
				900, 700, "라이브'러리 메인"));
		putFXMLProperty(LayoutsEnum.MainLayout, new FXMLProperty(getClass().getResource("mainLayoutFXML.fxml"),
				900, 700, "반납/대출"));
		putFXMLProperty(LayoutsEnum.RecentLogLayout, new FXMLProperty(getClass().getResource("recentLogLayoutFXML.fxml"),
				900, 700, "최근 자료 이용 이력"));
		putFXMLProperty(LayoutsEnum.NoReturnedLayout, new FXMLProperty(getClass().getResource("noReturnedLayoutFXML.fxml"),
				900, 700, "미반납 자료 조회"));
		putFXMLProperty(LayoutsEnum.AddNewBookLayout, new FXMLProperty(getClass().getResource("addNewBookLayoutFXML.fxml"),
				900, 700, "자료 입수"));
		putFXMLProperty(LayoutsEnum.GetDetailBookInfoLayout, new FXMLProperty(getClass().getResource("getDetailBookInfoLayoutFXML.fxml"),
				900, 700, "자료 상세 조회 및 수정/삭제"));
		putFXMLProperty(LayoutsEnum.RegisterStaffAccountLayout, new FXMLProperty(getClass().getResource("registerStaffAccountLayoutFXML.fxml"),
				900, 700, "직원 계정 등록"));
		putFXMLProperty(LayoutsEnum.GetDetailStaffAccountLayout, new FXMLProperty(getClass().getResource("getDetailStaffAccountLayoutFXML.fxml"),
				900, 700, "직원 계정 상세 조회 및 수정/삭제"));
	}
	
	public void switchTo(LayoutsEnum key) throws Exception {
		FXMLProperty fxmlProperty = getFXMLProperty(key);
		Parent root = this.parentMap.get(key);
		if (root == null) {
			root = FXMLLoader.load(fxmlProperty.getUrl());
			this.parentMap.put(key, root);
		}
		
		stage.setWidth(fxmlProperty.getPrefWidth());
		stage.setHeight(fxmlProperty.getPrefHeight());
		stage.setTitle(fxmlProperty.getTitle());
		
		try {
			if (scene == null) {
				scene = new Scene(root);
				stage.setScene(scene);
			} else {
				scene.setRoot(root);
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("stage가 null입니다. 아직 Stage의 객체가 주입되지 않았습니다.");
		}
	}
	
	public void switchToWithHide(LayoutsEnum key) throws Exception {
		FXMLProperty fxmlProperty = getFXMLProperty(key);
		Parent root = this.parentMap.get(key);
		if (root == null) {
			root = FXMLLoader.load(fxmlProperty.getUrl());
			this.parentMap.put(key, root);
		}
		
		stage.hide();
		stage.setWidth(fxmlProperty.getPrefWidth());
		stage.setHeight(fxmlProperty.getPrefHeight());
		stage.setTitle(fxmlProperty.getTitle());
		
		try {
			if (scene == null) {
				scene = new Scene(root);
				stage.setScene(scene);
			} else {
				scene.setRoot(root);
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("stage가 null입니다. 아직 Stage의 객체가 주입되지 않았습니다.");
		}
		
		stage.show();
	}
	
	public void freeParent(LayoutsEnum key) {
		parentMap.remove(key);
	}

	public void freeAll() {
		parentMap.clear();
	}
}
