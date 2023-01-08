package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetLoanBookRecordsByISBNController {

	public ObservableList<LoanVO> exec(String isbn, String startDate, String endDate) {
		LoanService service = new LoanService();
		return service.selectLoanBookRowsByISBNWithDates(isbn, startDate, endDate);
	}

}
