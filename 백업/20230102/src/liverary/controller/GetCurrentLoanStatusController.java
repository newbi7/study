package liverary.controller;

import java.util.HashMap;

import javafx.collections.ObservableList;
import liverary.service.LoanService;
import liverary.vo.LoanByAccountVO;

public class GetCurrentLoanStatusController {

	public HashMap<String, Integer> exec(int ano) {
		LoanService service = new LoanService();
		return service.selectLoanStatusOfAccount(ano);
	}

}
