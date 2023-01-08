package liverary.controller;

import javafx.collections.ObservableList;
import liverary.service.AccountService;
import liverary.vo.AccountVO;

public class GetAccountsByNameController {

	public ObservableList<AccountVO> exec(String name) {
		AccountService service = new AccountService();
		return service.selectAccountsByName(name);
	}
	
}
