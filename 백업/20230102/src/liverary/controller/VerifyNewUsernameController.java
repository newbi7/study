package liverary.controller;

import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class VerifyNewUsernameController {

	public boolean exec(String username) {
		AccountService service = new AccountService();
		AccountVO account = service.selectAccountbyUsername(username);
		
		if (account == null) {
			return true;
		}
		return false;
	}

}
