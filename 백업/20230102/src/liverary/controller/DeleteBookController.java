package liverary.controller;

import liverary.service.BookService;

public class DeleteBookController {

	public boolean exec(String isbn) {
		BookService service = new BookService();
		return service.deleteBook(isbn);
	}

}
