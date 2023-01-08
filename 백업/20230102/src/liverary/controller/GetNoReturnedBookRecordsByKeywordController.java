package liverary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetNoReturnedBookRecordsByKeywordController {

	public ObservableList<LoanVO> exec(String keyword, String startDate, String endDate) {
		LoanService service = new LoanService();
		return service.selectNoReturnedBookRowsByKeywordWithDates(keyword, startDate, endDate);
	}

}
