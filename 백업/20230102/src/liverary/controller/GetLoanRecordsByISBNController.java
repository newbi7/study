package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetLoanRecordsByISBNController {

	public ObservableList<LoanVO> exec(String isbn) {
		LoanService service = new LoanService();
		return service.selectLoanRecordsByISBN(isbn);
	}

}
