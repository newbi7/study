package lecture.jdbc.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import lecture.jdbc.service.PeopleService;
import lecture.jdbc.vo.PeopleVO;

public class Newassign {

	public Parent getRoot() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		gridPane.setAlignment(Pos.CENTER);

		Label title = new Label("회원 가입");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.setFont(Font.font(25));

		Label label1 = new Label("ID : ");
		TextField textf1 = new TextField();
		textf1.setPromptText(" ");

		Label label2 = new Label("PW : ");
		TextField textf2 = new TextField();
		textf2.setPromptText("  ");

		Label label3 = new Label("PW 확인 : ");
		TextField textf3 = new TextField();
		textf3.setPromptText(" ");
		
		Label label4 = new Label("성명 : ");
		TextField textf4 = new TextField();
		textf4.setPromptText(" ");
		
		Label label5 = new Label("주민번호 : ");
		TextField textf5 = new TextField();
		textf5.setPromptText("숫자만입력해주세요");
		
		Label label6 = new Label("휴대폰번호 : ");
		TextField textf6 = new TextField();
		textf6.setPromptText("숫자만입력해주세요");
		
		
		Button btn1 = new Button("닫기");
		Button btn2 = new Button("만들기");

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

		gridPane.add(btn1, 0, 10);
		gridPane.add(btn2, 1, 10);

		gridPane.add(title, 0, 0, 2, 1);

		gridPane.add(label1, 0, 1);
		gridPane.add(textf1, 1, 1);

		gridPane.add(label2, 0, 2);
		gridPane.add(textf2, 1, 2);

		gridPane.add(label3, 0, 3);
		gridPane.add(textf3, 1, 3);
		
		gridPane.add(label4, 0, 4);
		gridPane.add(textf4, 1, 4);

		gridPane.add(label5, 0, 5);
		gridPane.add(textf5, 1, 5);
		
		gridPane.add(label6, 0, 6);
		gridPane.add(textf6, 1, 6);

		gridPane.add(btmgridPane, 0, 8, 2, 1);

		btn1.setOnAction(e -> { 
			((Button)btn1).getScene().getWindow().hide();
		});

		
		
		btn2.setOnAction(e -> {
			String asid = textf1.getText().trim();
			String aspw = textf2.getText().trim();
			String asname = textf4.getText().trim();
			String assnumber = textf5.getText().trim();
			String asphone = textf6.getText().trim();
			PeopleService service = new PeopleService();
			String logid = textf1.getText().trim();
			ObservableList<PeopleVO> list= service.IDMatch(logid);
			String idsame = null;
			if(list.size()!=0) { idsame = list.get(0).getPid(); }
			
			if (textf1.getText().equals("") || textf2.getText().equals("") ||
					textf3.getText().equals("") || textf4.getText().equals("") ||
					textf5.getText().equals("") || textf6.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("빈칸이 있습니다.");
				alert.showAndWait();
			} else if (!textf2.getText().equals(textf3.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("비밀번호가 다릅니다.");
				alert.showAndWait();
				
			} else if (idsame!=null) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("존재하는 아이디입니다.");
				alert.showAndWait();
				
			} else {
				service.Newassign(asid, aspw, asname, assnumber, asphone);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("성공");
				alert.setHeaderText("회원가입이 되었습니다.");
				alert.showAndWait();
				((Button)btn2).getScene().getWindow().hide();
			}
			});

		return gridPane;
	}

	public Alert msgbox(AlertType type, String title, String text) {
		Alert msg = new Alert(type);
		msg.setHeaderText(null);
		msg.setTitle("PW찾기" + title);
		msg.setContentText(text);
		msg.show();
		return msg;
	}
}