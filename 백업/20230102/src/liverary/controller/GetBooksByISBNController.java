package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.BookService;
import liverary.vo.BookVO;

public class GetBooksByISBNController {

	public ObservableList<BookVO> exec(String isbn) {
		BookService service = new BookService();
		return service.selectBooksByISBN(isbn);
	}

}
