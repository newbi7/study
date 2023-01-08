package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetLoanRecordsByKeywordController {

	public ObservableList<LoanVO> exec(String keyword) {
		LoanService service = new LoanService();
		return service.selectLoanRecordsByKeyword(keyword);
	}

}
