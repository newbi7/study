package liverary.controller;

import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class NewAccountController {

	public boolean exec(AccountVO newAccount) {
		AccountService service = new AccountService();
		return service.insertNewAccount(newAccount);
	}

}
