package lecture.jdbc.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginView_1 extends Application {

	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		gridPane.setAlignment(Pos.CENTER);

		Label title = new Label("환영합니다. OO도서관입니다. ^^");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.setFont(Font.font(25));

		Label label1 = new Label("ID: ");
		TextField textf1 = new TextField();
		textf1.setPromptText("oooo@xxxx.xxx");

		Label label2 = new Label("PW: ");
		TextField textf2 = new TextField();
		textf2.setPromptText("PW를 입력해주세요.");

		Button btn1 = new Button("ID찾기");
		Button btn2 = new Button("PW찾기");
		Button btn3 = new Button("회원가입");
		Button btn4 = new Button("로그인");

		ColumnConstraints cc1 = new ColumnConstraints();
		ColumnConstraints cc2 = new ColumnConstraints();
		cc1.setPercentWidth(23);
		cc2.setPercentWidth(23);

		GridPane btmgridPane = new GridPane();
		btmgridPane.getColumnConstraints().addAll(cc1, cc2);
		btmgridPane.setHgap(10);
		btmgridPane.setAlignment(Pos.CENTER);

		btn1.setMaxWidth(Double.MAX_VALUE);
		btn2.setMaxWidth(Double.MAX_VALUE);
		btn3.setMaxWidth(Double.MAX_VALUE);
		btn4.setMaxWidth(Double.MAX_VALUE);

		btmgridPane.add(btn1, 0, 0);
		btmgridPane.add(btn2, 1, 0);
		btmgridPane.add(btn3, 2, 0);
		btmgridPane.add(btn4, 10, 0);

		gridPane.add(title, 0, 0, 2, 1);

		gridPane.add(label1, 0, 1);
		gridPane.add(textf1, 1, 1);

		gridPane.add(label2, 0, 2);
		gridPane.add(textf2, 1, 2);

		gridPane.add(btmgridPane, 0, 3, 2, 1);

		btn1.setOnAction(e -> { 
			IDFind idFind = new IDFind();
			Parent root = idFind.getRoot();
			Stage stage2 = new Stage();
		        stage2.setScene(new Scene(root, 400, 275));
		        stage2.initModality(Modality.WINDOW_MODAL);
		        stage2.show();

		});

		btn2.setOnAction(e -> {
			PWFind pwFind = new PWFind();
			Parent root = pwFind.getRoot();
			Stage stage2 = new Stage();
		        stage2.setScene(new Scene(root, 400, 300));
		        stage2.initModality(Modality.WINDOW_MODAL);
		        stage2.show();
		});

		btn3.setOnAction(e -> {
			SignUp signUp = new SignUp();
			Parent root = signUp.getRoot();
			Stage stage2 = new Stage();
		        stage2.setScene(new Scene(root, 400, 400));
		        stage2.initModality(Modality.WINDOW_MODAL);
		        stage2.show();
		});

		btn4.setOnAction(e-> {
			String id = textf1.getText().trim();
			String pw = textf2.getText().trim();
			
			if (id.equals("111") && pw.equals("111")) {
				Stage mainPage = new MainPageCu_3();
				((Button)btn4).getScene().getWindow().hide();
				
			} else if (id.equals("admin") && pw.equals("admin")) {
				Stage mainPage = new MainPageAd_2();
				((Button)btn4).getScene().getWindow().hide();
				
			} else {
				msgbox(AlertType.ERROR, "오류", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
		});

		stage.setTitle("로그인 메뉴");
		stage.setScene(new Scene(gridPane, 450, 200));
		stage.show();

		title.requestFocus();
	}


	public Alert msgbox(AlertType type, String title, String text) {
		Alert msg = new Alert(type);
		msg.setHeaderText(null);
		msg.setTitle("로그인 " + title);
		msg.setContentText(text);
		msg.show();
		return msg;
	}
}