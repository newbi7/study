package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetLoanBookRecordsByKeywordController {

	public ObservableList<LoanVO> exec(String keyword, String startDate, String endDate) {
		LoanService service = new LoanService();
		return service.selectLoanBookRowsByKeywordWithDates(keyword, startDate, endDate);
	}

}
