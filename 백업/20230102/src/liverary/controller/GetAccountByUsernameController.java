package liverary.controller;

import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class GetAccountByUsernameController {

	public AccountVO exec(String username) {
		AccountService service = new AccountService();
		return service.selectAccountbyUsername(username);
	}
	
}
