package lecture.jdbc.fxmlcontroller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookDetailInfc implements Initializable {

	String searchKeyword;
	ObservableList<BookVO> listM;

	@FXML private Button btitletextsearch;
	@FXML private TextField btitletextfield;

	@FXML private TableView<BookVO> bookinftableview;
	@FXML private TableColumn<BookVO, String> bisbn;
	@FXML private TableColumn<BookVO, String>  btitle;
	@FXML private TableColumn<BookVO, String>  bdate;
	@FXML private TableColumn<BookVO, Integer>  bpage;
	@FXML private TableColumn<BookVO, Integer> bprice;
	@FXML private TableColumn<BookVO, String> bauthor;
	@FXML private TableColumn<BookVO, String> btranslator;
	@FXML private TableColumn<BookVO, String> bsupplement;
	@FXML private TableColumn<BookVO, String> bpublisher;
	@FXML private TableColumn<BookVO, String> bimgurl;
	@FXML private void UpdateTable(){
		btitle.setCellValueFactory(new PropertyValueFactory<BookVO,String>("btitle"));
		bdate.setCellValueFactory(new PropertyValueFactory<BookVO,String>("bdate"));
		bpage.setCellValueFactory(new PropertyValueFactory<BookVO,Integer>("bpage"));
		bprice.setCellValueFactory(new PropertyValueFactory<BookVO,Integer>("bprice"));
		bauthor.setCellValueFactory(new PropertyValueFactory<BookVO,String>("bauthor"));
		btranslator.setCellValueFactory(new PropertyValueFactory<BookVO,String>("btranslator"));
		bsupplement.setCellValueFactory(new PropertyValueFactory<BookVO,String>("bsupplement"));
		bpublisher.setCellValueFactory(new PropertyValueFactory<BookVO,String>("bpublisher"));
		bimgurl.setCellValueFactory(new PropertyValueFactory<BookVO,String>("bimgurl"));

		bookinftableview.setItems(listM);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 책검색작동
		btitletextsearch.setOnAction(e -> {

			BookService service = new BookService();
			ObservableList<BookVO> detaillist = 
					service.selectdetailBooksByKeyword(btitletextfield.getText());

			UpdateTable();
			bookinftableview.setItems(detaillist);

			searchKeyword = btitletextfield.getText();
			btitletextfield.clear();
		});
		btitletextfield.setOnAction(e -> {
			
			BookService service = new BookService();
			ObservableList<BookVO> detaillist = 
					service.selectdetailBooksByKeyword(btitletextfield.getText());

			UpdateTable();
			bookinftableview.setItems(detaillist);

			searchKeyword = btitletextfield.getText();
			btitletextfield.clear();
		});
		// 책검색작동
	}
}
