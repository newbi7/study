	package lecture.jdbc.fxmlcontroller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import lecture.jdbc.service.BookInfService;
import lecture.jdbc.vo.BookInfVO;

public class BookNonPaymentc implements Initializable {

	ObservableList<BookInfVO> listM;
	
	@FXML private Button npaybutton;

	@FXML private TableView<BookInfVO> BookInfTableView;
	@FXML private TableColumn<BookInfVO, String> pid;
	@FXML private TableColumn<BookInfVO, String> bisbn;
	@FXML private TableColumn<BookInfVO, String>  btitle;
	@FXML private TableColumn<BookInfVO, String>  bdate;
	@FXML private TableColumn<BookInfVO, Integer>  bpage;
	@FXML private TableColumn<BookInfVO, Integer> bprice;
	@FXML private TableColumn<BookInfVO, String> bauthor;
	@FXML private TableColumn<BookInfVO, Integer> bborrowdate;
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
		bhave.setCellValueFactory(new PropertyValueFactory<BookInfVO,Integer>("bhave"));

		BookInfTableView.setItems(listM);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 책검색작동
		npaybutton.setOnAction(e -> {
			BookInfService service = new BookInfService();
			listM = service.selectBooksInf();
			UpdateTable();
		
			if(listM.isEmpty()) {			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("반납 확인");
				alert.setHeaderText("미납 책이 없습니다.");
				alert.showAndWait();;
			}
		});
		// 책검색작동
	}
}
