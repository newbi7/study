package liverary.controller;

import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class ReturnBookController {

	public Boolean exec(LoanVO selectedBook) {
		LoanService service = new LoanService();
		return service.updateLoanRecord(selectedBook);
	}

}
