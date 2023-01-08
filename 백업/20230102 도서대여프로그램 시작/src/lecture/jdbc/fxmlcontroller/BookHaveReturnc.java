package lecture.jdbc.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lecture.jdbc.service.BookInfService;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookInfVO;

public class BookHaveReturnc implements Initializable {

	String bookSearchClickInf;
	String searchKeyword;
	String idtext;
	ObservableList<BookInfVO> listM;

	@FXML private Button idsearchbutton;
	@FXML private TextField idsearchtextfield;
	@FXML private Button bookreturn;

	@FXML private TableView<BookInfVO> BookInfTableView;
	@FXML private TableColumn<BookInfVO, String> pid;
	@FXML private TableColumn<BookInfVO, String> bisbn;
	@FXML private TableColumn<BookInfVO, String>  btitle;
	@FXML private TableColumn<BookInfVO, String>  bdate;
	@FXML private TableColumn<BookInfVO, Integer>  bpage;
	@FXML private TableColumn<BookInfVO, Integer> bprice;
	@FXML private TableColumn<BookInfVO, String> bauthor;
	@FXML private TableColumn<BookInfVO, Integer>  bborrowdate;
	@FXML private TableColumn<BookInfVO, Integer> bpoint;
	@FXML private TableColumn<BookInfVO, Integer>  bfuturepoint;
	@FXML private TableColumn<BookInfVO, Integer> bhave;
	@FXML private void UpdateTable(){
		pid.setCellValueFactory(new PropertyValueFactory<BookInfVO,String>("pid"));
		bisbn.setCellValueFactory(new PropertyValueFactory<BookInfVO,String>("bisbn"));
		btitle.setCellValueFactory(new PropertyValueFactory<BookInfVO,String>("btitle"));
		bdate.setCellValueFactory(new PropertyValueFactory<BookInfVO,String>("bdate"));
		bpage.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bpage"));
		bprice.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bprice"));
		bauthor.setCellValueFactory(new PropertyValueFactory<BookInfVO,String>("bauthor"));
		bborrowdate.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bborrowdate"));
		bpoint.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bpoint"));
		bfuturepoint.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bfuturepoint"));
		bhave.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bhave"));

		BookInfTableView.setItems(listM);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 책검색작동
		idsearchbutton.setOnAction(e -> {

			BookInfService service = new BookInfService();
			ObservableList<BookInfVO> list = 
					service.selectBooksInf(idsearchtextfield.getText());

			UpdateTable();
			BookInfTableView.setItems(list);

			searchKeyword = idsearchtextfield.getText();
			idsearchtextfield.clear();
		});
		idsearchtextfield.setOnAction(e -> {

			BookInfService service = new BookInfService();
			ObservableList<BookInfVO> list = 
					service.selectBooksInf(idsearchtextfield.getText());

			UpdateTable();
			BookInfTableView.setItems(list);

			searchKeyword = idsearchtextfield.getText();
			idsearchtextfield.clear();
		});
		// 책검색작동

		BookInfTableView.setRowFactory(e -> {
			TableRow<BookInfVO> row = new TableRow<>();
			row.setOnMouseClicked(e1 -> {
				BookInfVO book = row.getItem();
				bookSearchClickInf = book.getBisbn();
			});
			return row;
		});
		
		System.out.println(bookSearchClickInf);
		bookreturn.setOnAction(e -> {
			BookInfService service = new BookInfService(); 
			service.Bookreturn(bookSearchClickInf);
			BookService service1 = new BookService(); 
			service1.BookreturnVO(bookSearchClickInf);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("성공");
			alert.setHeaderText("책을 반납하였습니다.");
			alert.showAndWait();
		});

	}

	public void setArgAndRender(String idtext) {
		this.idtext = idtext;
		idsearchtextfield.setText(idtext);

		if(idsearchtextfield.getText().equals("admin")) {
			idsearchtextfield.setEditable(true);
		} else {
			idsearchtextfield.setEditable(false);
		}
	}
}