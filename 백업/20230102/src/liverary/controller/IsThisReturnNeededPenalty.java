package liverary.controller;

import liverary.service.LoanService;
import liverary.vo.LoanVO;

public class IsThisReturnNeededPenalty {

	public boolean exec(LoanVO record) {
		LoanService service = new LoanService();
		return service.getIsNeededPenalty(record);
	}

}
