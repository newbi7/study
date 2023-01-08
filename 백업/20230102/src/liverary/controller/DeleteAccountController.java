package liverary.controller;

import liverary.service.AccountService;

public class DeleteAccountController {

	public int exec(int ano) {
		AccountService service = new AccountService();
		return service.updateAccountToEmptyRow(ano);
	}

}
