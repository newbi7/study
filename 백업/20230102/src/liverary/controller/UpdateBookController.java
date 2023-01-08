package liverary.controller;

import liverary.service.BookService;
import liverary.vo.BookVO;

public class UpdateBookController {

	public boolean exec(BookVO newBook) {
		BookService service = new BookService();
		return service.updateBook(newBook);
	}

}
