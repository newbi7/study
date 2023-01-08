package liverary.controller;

import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class GetAccountByNoController {
	
	public AccountVO exec(int no) {
		AccountService service = new AccountService();
		return service.selectAccountbyNO(no);
	}
}
