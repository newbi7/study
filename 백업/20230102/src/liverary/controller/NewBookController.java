package liverary.controller;

import liverary.service.BookService;
import liverary.vo.BookVO;

public class NewBookController {

	public boolean exec(BookVO newBook) {
		BookService service = new BookService();
		return service.insertNewBook(newBook);
	}

}
