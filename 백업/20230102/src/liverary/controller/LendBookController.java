package liverary.controller;

import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class LendBookController {

	public boolean exec(LoanVO selectedBook) {
		LoanService service = new LoanService();
		return service.insertLoanRecord(selectedBook);
	}

}
