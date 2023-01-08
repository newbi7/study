package liverary.controller;

import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class UpdateAccountController {

	public boolean exec(AccountVO account) {
		AccountService service = new AccountService();
		return service.updateAccount(account);
	}

}
