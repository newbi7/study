package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.LoanService;

public class GetLoanRecordsByAnoController {

	public ObservableList exec(int ano) {
		LoanService service = new LoanService();
		return service.selectLoanRowOfAccount(ano);
	}

}
