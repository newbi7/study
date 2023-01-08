package liverary.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import liverary.controller.DeleteBookController;
import liverary.controller.GetABookByISBNController;
import liverary.controller.GetLoanRecordsByISBNController;
import liverary.controller.UpdateBookController;
import liverary.vo.BookVO;
import liverary.vo.LoanVO;

public class EditBookInfoModal implements Initializable {
	
	@FXML private Label greetingLabel;
	@FXML private Label additionalInfoLabel;
	@FXML private Hyperlink logoutLink;
	
	@FXML private TextField isbnTextField;
	@FXML private TextField titleTextField;
	@FXML private TextField yearTextField;
	@FXML private TextField monthTextField;
	@FXML private TextField pageTextField;
	@FXML private TextField priceTextField;

	@FXML private TextField authorTextField;
	@FXML private TextField translatorTextField;
	@FXML private TextArea supplementTextField;
	@FXML private TextField publisherTextField;
	
	@FXML private Button editBtn;
	@FXML private Button deleteBtn;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
	}
	
	public void getDataAndSetTableView(String targetISBN) {
		GetABookByISBNController controller = new GetABookByISBNController();
		BookVO book = controller.exec(targetISBN);
		
		String date = book.getBdate();
		String year = date.split("년")[0];
		String month = date.split("년")[1].split("월")[0].trim();
		
		isbnTextField.setText(book.getBisbn());
		titleTextField.setText(book.getBtitle());
		yearTextField.setText(year);
		monthTextField.setText(month); 
		pageTextField.setText(String.valueOf(book.getBpage()));
		priceTextField.setText(String.valueOf(book.getBprice()));
		authorTextField.setText(book.getBauthor());
		translatorTextField.setText(book.getBtranslator());
		supplementTextField.setText(book.getBsupplement());
		publisherTextField.setText(book.getBpublisher());
	}
	
	@FXML
	private void handleEditBtn(ActionEvent e) {
		String isbn = isbnTextField.getText();      
		String title = titleTextField.getText();
		String year = yearTextField.getText();
		String month = monthTextField.getText();
		String page_str = pageTextField.getText();
		String price_str = priceTextField.getText();
		String author = authorTextField.getText();
		String translator = translatorTextField.getText();
		String supplement = supplementTextField.getText();
		String publisher = publisherTextField.getText();
		
		if ("".equals(isbn) || "".equals(title) || "".equals(year) ||
				 "".equals(month) || "".equals(page_str) || "".equals(price_str) ||
				 "".equals(author) || "".equals(publisher)) {
			(new Alert(
					AlertType.ERROR, "항목을 빠짐없이 입력해주세요. 역자와 딸림자료만 공란으로 남길 수 있습니다.")).showAndWait();
			return;
		}
		
		int page = Integer.parseInt(page_str);
		int price = Integer.parseInt(price_str);
			
		BookVO newBook = new BookVO(isbn, title, price, author, translator, publisher,
				(year + "년 " + month + "월"), page, supplement);
		
		UpdateBookController controller = new UpdateBookController();
		boolean success = controller.exec(newBook);
		
		if (success) {
			(new Alert(
					AlertType.INFORMATION, "성공적으로 서지사항을 수정했습니다.")).showAndWait();
			
			Node node = (Node) e.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    thisStage.close();
		} else {
			(new Alert(
					AlertType.ERROR, "자료 등록 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		}
	}
	
	@FXML
	private void handleDeleteBtn(ActionEvent e) {
		String isbn = isbnTextField.getText();
		
		Alert alert = 
		        new Alert(AlertType.WARNING, 
		            "정말로 이 항목을 삭제하시겠습니까? 이 작업은 돌이킬 수 없습니다.",
		             ButtonType.OK, 
		             ButtonType.CANCEL);
		alert.setTitle("항목 삭제 경고");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.CANCEL) {
		    return;
		}
		
		GetLoanRecordsByISBNController loanController = new GetLoanRecordsByISBNController();
		ObservableList<LoanVO> list = loanController.exec(isbn);
		if (!list.isEmpty()) {
			(new Alert(
					AlertType.ERROR, "해당 자료에 대한 대출/반납 이력이 존재하여 삭제할 수 없습니다.")).showAndWait();
			return;
		}
		
		DeleteBookController controller = new DeleteBookController();
		boolean success = controller.exec(isbn);
		
		if (success) {
			(new Alert(
					AlertType.INFORMATION, "성공적으로 항목을 삭제했습니다.")).showAndWait();
			
			Node node = (Node) e.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    thisStage.close();
		} else {
			(new Alert(
					AlertType.ERROR, "삭제 작업 중 문제가 발생했습니다. 오류가 지속되면 관리자에게 문의하십시오.")).showAndWait();
		}
	}
}
