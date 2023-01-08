package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.BookService;
import liverary.vo.BookVO;

public class GetBooksByKeywordController {

	public ObservableList<BookVO> exec(String query) {
		BookService service = new BookService();
		return service.selectBooksByKeyword(query);
	}

}
