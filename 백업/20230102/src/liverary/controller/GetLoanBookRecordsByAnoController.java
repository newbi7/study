package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class GetLoanBookRecordsByAnoController {

	public ObservableList<LoanVO> exec(int ano) {
		LoanService service = new LoanService();
		return service.selectLoanBookRowsOfAccount(ano);
	}

}
