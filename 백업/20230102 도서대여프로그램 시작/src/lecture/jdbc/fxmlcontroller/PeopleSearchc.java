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
import lecture.jdbc.service.PeopleService;
import lecture.jdbc.vo.PeopleVO;

public class PeopleSearchc implements Initializable {

	String searchKeyword;
	ObservableList<PeopleVO> listM;

	@FXML private Button SearchButton;
	@FXML private TextField PeoplesearchText;

	@FXML private TableView<PeopleVO> PeopleTableView;
	@FXML private TableColumn<PeopleVO, String> pid;
	@FXML private TableColumn<PeopleVO, String>  ppw;
	@FXML private TableColumn<PeopleVO, String>  pname;
	@FXML private TableColumn<PeopleVO, Integer>  pssnumber;
	@FXML private TableColumn<PeopleVO, Integer> pphone;
	@FXML private TableColumn<PeopleVO, Integer> pbnumber;
	@FXML private TableColumn<PeopleVO, Integer> ppoint;
	@FXML private void UpdateTable(){
		pid.setCellValueFactory(new PropertyValueFactory<PeopleVO,String>("pid"));
		ppw.setCellValueFactory(new PropertyValueFactory<PeopleVO,String>("ppw"));
		pname.setCellValueFactory(new PropertyValueFactory<PeopleVO,String>("pname"));
		pssnumber.setCellValueFactory(new PropertyValueFactory<PeopleVO,Integer>("pssnumber"));
		pphone.setCellValueFactory(new PropertyValueFactory<PeopleVO,Integer>("pphone"));
		pbnumber.setCellValueFactory(new PropertyValueFactory<PeopleVO,Integer>("pbnumber"));
		ppoint.setCellValueFactory(new PropertyValueFactory<PeopleVO,Integer>("ppoint"));

		PeopleTableView.setItems(listM);
	}
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			// 사람검색작동
			SearchButton.setOnAction(e -> {
				
				PeopleService service = new PeopleService();
				ObservableList<PeopleVO> list = 
						service.selectPeopleByKeyword(PeoplesearchText.getText());

				UpdateTable();
				PeopleTableView.setItems(list);

				searchKeyword = PeoplesearchText.getText();
				PeoplesearchText.clear();
				
			});
			PeoplesearchText.setOnAction(e -> {
				
				PeopleService service = new PeopleService();
				ObservableList<PeopleVO> list = 
						service.selectPeopleByKeyword(PeoplesearchText.getText());
				UpdateTable();
				PeopleTableView.setItems(list);

				searchKeyword = PeoplesearchText.getText();
				PeoplesearchText.clear();
			});
			// 사람검색작동
			
	}
}
