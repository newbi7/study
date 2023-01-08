package liverary.controller;

import liverary.service.BookService;
import liverary.vo.BookVO;

public class GetABookByISBNController {

	public BookVO exec(String isbn) {
		BookService service = new BookService();
		return service.selectABookByISBN(isbn);
	}

}
