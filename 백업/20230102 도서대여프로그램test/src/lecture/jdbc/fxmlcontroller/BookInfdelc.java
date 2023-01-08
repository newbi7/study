package lecture.jdbc.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lecture.jdbc.service.BookService;
import lecture.jdbc.service.PeopleService;
import lecture.jdbc.vo.BookVO;
import lecture.jdbc.vo.PeopleVO;

public class BookInfdelc implements Initializable {

	MainPageAdc_2 mainpageadc_2 = new MainPageAdc_2();
	String bookSearchClick;

	@FXML private Button bookdelete;
	@FXML private Button bookinfins;
	@FXML private TextField bisbn;
	@FXML private TextField nbisbn;
	@FXML private TextField btitle;
	@FXML private TextField nbtitle;
	@FXML private TextField bdate;
	@FXML private TextField nbdate;
	@FXML private TextField bpage;
	@FXML private TextField nbpage;
	@FXML private TextField bprice;
	@FXML private TextField nbprice;
	@FXML private TextField bauthor;
	@FXML private TextField nbauthor;
	@FXML private TextField btranslator;
	@FXML private TextField nbtranslator;
	@FXML private TextField bsupplement;
	@FXML private TextField nbsupplement;
	@FXML private TextField bpublisher;
	@FXML private TextField nbpublisher;
	@FXML private TextField bimgurl;
	@FXML private TextField nbimgurl;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookdelete.setOnAction(e -> {
			BookService service = new BookService(); 
			service.DeleteBook(bookSearchClick);
			Alert alert = new Alert(AlertType.INFORMATION); 
			alert.setTitle("성공");
			alert.setHeaderText("책을 삭제하였습니다."); alert.showAndWait();
			((Button)bookdelete).getScene().getWindow().hide();
		});

		bookinfins.setOnAction(e -> {
			BookService service = new BookService(); 
			if (nbisbn.getText().equals("") || nbtitle.getText().equals("")
					|| nbdate.getText().equals("") || nbpage.getText().equals("")
					|| nbprice.getText().equals("") || nbauthor.getText().equals("")
					|| nbtranslator.getText().equals("") || nbsupplement.getText().equals("")
					|| nbpublisher.getText().equals("") || nbimgurl.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("빈칸이 있습니다.");
				alert.showAndWait();
			} else {
				service.Bookinfins(nbisbn.getText(), nbtitle.getText(), 
						nbdate.getText(), Integer.parseInt(nbpage.getText()),
						Integer.parseInt(nbprice.getText()), nbauthor.getText(),
						nbtranslator.getText(), nbsupplement.getText(),
						nbpublisher.getText(), nbimgurl.getText(), bisbn.getText());

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("성공");
				alert.setHeaderText("책 정보를 수정하였습니다.");
				alert.showAndWait();
				((Button)bookinfins).getScene().getWindow().hide();
			}

		});
	}

	public void setArgAndRender(String bookSearchClick) {
		this.bookSearchClick = bookSearchClick;
		bisbn.setText(bookSearchClick);

		BookService service = new BookService();
		BookVO bookinf = service.Bookinfstyle(bookSearchClick);
		btitle.setText(bookinf.getBtitle());
		bdate.setText(bookinf.getBdate());
		bpage.setText(String.valueOf(bookinf.getBpage()));
		bprice.setText(String.valueOf(bookinf.getBprice()));
		bauthor.setText(bookinf.getBauthor());
		btranslator.setText(bookinf.getBtranslator());
		bsupplement.setText(bookinf.getBsupplement());
		bpublisher.setText(bookinf.getBpublisher());
		bimgurl.setText(bookinf.getBimgurl());
	}
}
